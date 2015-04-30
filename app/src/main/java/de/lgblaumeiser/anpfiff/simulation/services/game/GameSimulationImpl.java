/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.game;

import java.util.Map;
import java.util.Random;

import de.lgblaumeiser.anpfiff.simulation.model.FootballTeam;
import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.ShapeReduction;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Maps.newHashMap;

/**
 * Service implementation of the game simulation
 *
 * @author Lars Geyer-Blaumeiser
 */
class GameSimulationImpl implements GameSimulation {
	private final Random randomGenerator = new Random();

	private static final Map<Integer, Double[]> differenceToPercentageMap = newHashMap();
	private static final Map<Integer, Integer[]> differenceToGoalsForPercentageMap = newHashMap();

	static {
		differenceToPercentageMap.put(-5, new Double[] { 1.0 });
		differenceToGoalsForPercentageMap.put(-5, new Integer[] { 0 });
		differenceToPercentageMap.put(-4, new Double[] { 0.17, 1.0 });
		differenceToGoalsForPercentageMap.put(-4, new Integer[] { 1, 0 });
		differenceToPercentageMap.put(-3, new Double[] { 0.17, 1.0 });
		differenceToGoalsForPercentageMap.put(-3, new Integer[] { 1, 0 });
		differenceToPercentageMap.put(-2, new Double[] { 0.25, 1.0 });
		differenceToGoalsForPercentageMap.put(-2, new Integer[] { 1, 0 });
		differenceToPercentageMap.put(-1, new Double[] { 0.25, 1.0 });
		differenceToGoalsForPercentageMap.put(-1, new Integer[] { 1, 0 });
		differenceToPercentageMap.put(0, new Double[] { 0.5, 1.0 });
		differenceToGoalsForPercentageMap.put(0, new Integer[] { 1, 0 });
		differenceToPercentageMap.put(1, new Double[] { 0.67, 1.0 });
		differenceToGoalsForPercentageMap.put(1, new Integer[] { 1, 0 });
		differenceToPercentageMap.put(2, new Double[] { 0.33, 0.67, 1.0 });
		differenceToGoalsForPercentageMap.put(2, new Integer[] { 2, 1, 0 });
		differenceToPercentageMap.put(3, new Double[] { 0.5, 0.75, 1.0 });
		differenceToGoalsForPercentageMap.put(3, new Integer[] { 2, 1, 0 });
		differenceToPercentageMap.put(4, new Double[] { 0.2, 0.6, 0.8, 1.0 });
		differenceToGoalsForPercentageMap.put(4, new Integer[] { 3, 2, 1, 0 });
		differenceToPercentageMap.put(5, new Double[] { 0.17, 0.5, 0.67, 0.83, 1.0 });
		differenceToGoalsForPercentageMap.put(5, new Integer[] { 4, 3, 2, 1, 0 });
		differenceToPercentageMap.put(6, new Double[] { 0.43, 0.71, 0.86, 1.0 });
		differenceToGoalsForPercentageMap.put(6, new Integer[] { 4, 3, 2, 0 });
		differenceToPercentageMap.put(7, new Double[] { 0.43, 0.71, 0.86, 1.0 });
		differenceToGoalsForPercentageMap.put(7, new Integer[] { 4, 3, 2, 1 });
		differenceToPercentageMap.put(8, new Double[] { 0.43, 0.71, 1.0 });
		differenceToGoalsForPercentageMap.put(8, new Integer[] { 4, 3, 2 });

	}

	@Override
	public GameResult simulateGame(final Game game) {
		checkNotNull(game);
		int homegoals = 0;
		int guestgoals = 0;

		final FootballTeam hometeam = game.getHometeam();
		final FootballTeam guestteam = game.getGuestteam();

		final boolean[] teamshapegoals = randomTeamShapeGoals(hometeam, guestteam);
		if (teamshapegoals[0]) {
			homegoals++;
		}
		if (teamshapegoals[1]) {
			guestgoals++;
		}

		homegoals += teamStrengthGoals(hometeam, guestteam);
		guestgoals += teamStrengthGoals(guestteam, hometeam);

		if (randomHometeamGoal()) {
			homegoals++;
		}

		if (randomDayGoal()) {
			homegoals++;
		}
		if (randomDayGoal()) {
			guestgoals++;
		}

		final ShapeReduction homeshapechange = shapeChange();
		final ShapeReduction guestshapechange = shapeChange();

		return new GameResult(game, homegoals, guestgoals, homeshapechange, guestshapechange);
	}

	private ShapeReduction shapeChange() {
		final double randomValue = randomGenerator.nextDouble();
		if (randomValue <= 0.33) {
			return ShapeReduction.LOW;
		}
		if (randomValue <= 0.67) {
			return ShapeReduction.MEDIUM;
		}
		return ShapeReduction.HIGH;
	}

	private static final double POSSIBILITY_FOR_RANDOM_GOAL = 0.1;

	// With a certain possibility a goal is scored for either team for a pure
	// random reason. This method returns true when a team scores randomly.
	private boolean randomDayGoal() {
		return randomGenerator.nextDouble() < POSSIBILITY_FOR_RANDOM_GOAL;
	}

	// With a certain possibility the home team scores a goal because it is the
	// home team, if this method returns true, the home team scores an
	// additional goal
	private boolean randomHometeamGoal() {
		return randomGenerator.nextBoolean();
	}

	// Calculates the number of goals the attacking team scores. This depends on
	// the difference in strength between the attacking players and the
	// defending players of the defending team.
	private int teamStrengthGoals(final FootballTeam attackingteam, final FootballTeam defendingteam) {
		final int offensiveStrength = attackingteam.getOffensiveStrength();
		final int defensiveStrength = defendingteam.getDefensiveStrength();

		final int difference = limitDifference(offensiveStrength - defensiveStrength);

		return randomGoalsOfDifference(difference);
	}

	private int randomGoalsOfDifference(final int difference) {
		final Double[] percentages = differenceToPercentageMap.get(difference);
		final Integer[] goals = differenceToGoalsForPercentageMap.get(difference);

		final double randomValue = randomGenerator.nextDouble();
		for (int index = 0; index < percentages.length; index++) {
			if (randomValue <= percentages[index]) {
				return goals[index];
			}
		}
		throw new IllegalStateException();
	}

	private int limitDifference(final int difference) {
		if (difference < -5) {
			return -5;
		}
		if (difference > 8) {
			return 8;
		}
		return difference;
	}

	// Returns a boolean field with two indices. If first is true, the home team
	// scored a goal due to the team shape difference, if the second is true the
	// guest team scored a goal due to the team shape difference
	private boolean[] randomTeamShapeGoals(final FootballTeam hometeam, final FootballTeam guestteam) {
		final int teamshapegoalindex = hometeam.getCurrentTeamshape() >= guestteam.getCurrentTeamshape() ? 0 : 1;
		final boolean teamshapegoal = randomGenerator.nextBoolean();
		final boolean[] teamshapegoalfield = new boolean[2];
		teamshapegoalfield[0] = false;
		teamshapegoalfield[1] = false;
		teamshapegoalfield[teamshapegoalindex] = teamshapegoal;
		return teamshapegoalfield;
	}
}
