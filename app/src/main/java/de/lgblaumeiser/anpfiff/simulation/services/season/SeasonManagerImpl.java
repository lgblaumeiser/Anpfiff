/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.season;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import de.lgblaumeiser.anpfiff.simulation.model.FootballTeam;
import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GamePlan;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.Season;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;
import de.lgblaumeiser.anpfiff.simulation.model.TableEntry;
import de.lgblaumeiser.anpfiff.simulation.persistency.PersistencyService;
import de.lgblaumeiser.anpfiff.simulation.services.game.GameSimulation;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static java.util.Collections.shuffle;
import static java.util.Collections.sort;

/**
 * Implementation of the SeasonManager service interface
 *
 * @author Lars Geyer-Blaumeiser
 */
class SeasonManagerImpl implements SeasonManager {
	private PersistencyService persistency;

	private static final Map<Integer, Integer[]> gameIndexMap = Maps
			.newHashMapWithExpectedSize(SeasonConstants.NUMBER_OF_GAME_DAYS_PER_HALF_SEASON);

	static {
		gameIndexMap.put(0, new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 0 });
		gameIndexMap.put(1, new Integer[] { 4, 13, 14, 7, 2, 9, 8, 3, 0, 11, 12, 15, 6, 1, 16, 5, 10, 17 });
		gameIndexMap.put(2, new Integer[] { 14, 8, 1, 0, 13, 10, 3, 2, 7, 16, 15, 11, 17, 6, 9, 4, 5, 12 });
		gameIndexMap.put(3, new Integer[] { 10, 7, 6, 9, 4, 8, 0, 3, 12, 1, 15, 5, 16, 13, 2, 14, 11, 17 });
		gameIndexMap.put(4, new Integer[] { 1, 15, 3, 10, 8, 6, 9, 16, 13, 0, 14, 4, 17, 12, 5, 11, 7, 2 });
		gameIndexMap.put(5, new Integer[] { 16, 3, 6, 13, 10, 14, 0, 5, 11, 1, 15, 17, 2, 8, 4, 7, 12, 9 });
		gameIndexMap.put(6, new Integer[] { 7, 0, 1, 5, 13, 12, 14, 15, 3, 6, 8, 10, 9, 11, 2, 4, 17, 16 });
		gameIndexMap.put(7, new Integer[] { 1, 8, 16, 4, 5, 17, 10, 2, 0, 14, 11, 13, 6, 7, 12, 3, 15, 9 });
		gameIndexMap.put(8, new Integer[] { 8, 11, 13, 5, 4, 10, 3, 15, 7, 12, 9, 0, 14, 6, 2, 16, 17, 1 });
		gameIndexMap.put(9, new Integer[] { 6, 4, 16, 8, 5, 9, 0, 2, 12, 14, 1, 13, 17, 3, 11, 10, 15, 7 });
		gameIndexMap.put(10, new Integer[] { 7, 5, 14, 16, 4, 15, 3, 11, 9, 1, 10, 6, 8, 0, 2, 12, 13, 17 });
		gameIndexMap.put(11, new Integer[] { 1, 3, 6, 2, 17, 9, 16, 10, 5, 14, 15, 13, 11, 7, 12, 8, 0, 4 });
		gameIndexMap.put(12, new Integer[] { 10, 0, 6, 16, 14, 11, 4, 12, 7, 1, 8, 15, 3, 5, 2, 17, 9, 13 });
		gameIndexMap.put(13, new Integer[] { 13, 3, 17, 7, 5, 2, 0, 6, 11, 4, 15, 10, 1, 14, 12, 16, 9, 8 });
		gameIndexMap.put(14, new Integer[] { 3, 9, 6, 11, 4, 1, 7, 13, 8, 5, 10, 12, 16, 0, 14, 17, 2, 15 });
		gameIndexMap.put(15, new Integer[] { 1, 10, 5, 4, 12, 0, 11, 16, 13, 2, 17, 8, 3, 14, 9, 7, 15, 6 });
		gameIndexMap.put(16, new Integer[] { 16, 1, 6, 12, 14, 9, 4, 17, 8, 13, 0, 15, 2, 11, 7, 3, 10, 5 });
	}

    private GameSimulation gameSimulation;

	private Season season;

	private List<TableEntry> table;

	private int lastGameDay;

	private List<GameResult> lastGameResults;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.lgblaumeiser.anpfiff.services.season.SeasonManager#newSeason()
	 */
	@Override
	public SeasonManager newSeason() {
        checkNotNull(gameSimulation);
        checkNotNull(persistency);
		final List<FootballTeam> teams = persistency.loadInitialTeamData();
		final GamePlan gamePlan = createGamePlan(teams);
		season = new Season(teams, gamePlan);
		table = Lists.newArrayListWithCapacity(SeasonConstants.NUMBER_OF_TEAMS);
		for (final FootballTeam team : teams) {
			table.add(new TableEntry(team, 0, 0, 0, 0, 0));
		}
		lastGameDay = 0;
		return this;
	}

	private GamePlan createGamePlan(final List<FootballTeam> teams) {
		final List<List<Game>> gameDays = Lists.newArrayListWithCapacity(SeasonConstants.NUMBER_OF_GAME_DAYS);
		for (int index = 0; index < SeasonConstants.NUMBER_OF_GAME_DAYS; index++) {
			gameDays.add(Lists.<Game>newArrayListWithCapacity(SeasonConstants.NUMBER_OF_GAMES_PER_DAY));
		}
		final List<FootballTeam> teamsCopy = Lists.newArrayList(teams);
		shuffle(teamsCopy);

		for (int gameday = 0; gameday < SeasonConstants.NUMBER_OF_GAME_DAYS_PER_HALF_SEASON; gameday++) {
			final List<Game> firstGameDay = gameDays.get(gameday);
			final List<Game> backGameDay = gameDays.get(gameday + SeasonConstants.NUMBER_OF_GAME_DAYS_PER_HALF_SEASON);
			final Integer[] gamedayIndices = gameIndexMap.get(gameday);

			for (int gameIndex = 0; gameIndex < SeasonConstants.NUMBER_OF_TEAMS; gameIndex += 2) {
				firstGameDay.add(new Game(teamsCopy.get(gamedayIndices[gameIndex]), teamsCopy
						.get(gamedayIndices[gameIndex + 1])));
				backGameDay.add(new Game(teamsCopy.get(gamedayIndices[gameIndex + 1]), teamsCopy
						.get(gamedayIndices[gameIndex])));
			}
		}
		return new GamePlan(gameDays);
	}


	@Override
	public int getCurrentGameDay() {
		checkNotNull(gameSimulation);
		checkNotNull(persistency);
		return lastGameDay;
	}

	@Override
	public List<Game> getNextGameDay() {
		checkNotNull(gameSimulation);
		checkNotNull(persistency);
		checkState(lastGameDay < SeasonConstants.NUMBER_OF_GAME_DAYS);
		return season.getGameDay(lastGameDay+1);
	}

	@Override
	public SeasonManager playNextGameDay() {
        checkNotNull(gameSimulation);
        checkNotNull(persistency);
        lastGameDay++;
		checkState(lastGameDay <= SeasonConstants.NUMBER_OF_GAME_DAYS);
		checkShapeReductionInitialization();
		final List<Game> games = season.getGameDay(lastGameDay);
		final List<GameResult> results = Lists.newArrayListWithCapacity(games.size());
		for (final Game game : games) {
			results.add(gameSimulation.simulateGame(game));
		}
		lastGameResults = results;
		computeNewTable();
		handleTeamShape();
		return this;
	}

	private void checkShapeReductionInitialization() {
		if (firstGameDayAfterPreparation()) {
			for (final TableEntry team : table) {
				team.getTeam().initializeTeamshape();
			}
		}
	}

	private boolean firstGameDayAfterPreparation() {
		return lastGameDay == 1 || lastGameDay == SeasonConstants.NUMBER_OF_GAME_DAYS_PER_HALF_SEASON + 1
				&& SeasonConstants.WINTER_BREAK_AFTER_HALF_SEASON;
	}

	private void handleTeamShape() {
		for (final GameResult result : lastGameResults) {
			result.getHometeam().reduceCurrentTeamshape(result.getHomeshapereduction());
			result.getGuestteam().reduceCurrentTeamshape(result.getGuestshapereduction());
		}
	}

	private static final class ResultCalculator {
		private final int goalsShot;
		private final int goalsReceived;

		private ResultCalculator(int goalsShot, int goalsReceived) {
			this.goalsShot = goalsShot;
			this.goalsReceived = goalsReceived;
		}

		private int getGoalsShot() {
			return goalsShot;
		}

		private int getGoalsReceived() {
			return goalsReceived;
		}

		private int getWonAdder() {
			return goalsShot > goalsReceived ? 1 : 0;
		}

		private int getDrawAdder() {
			return goalsShot == goalsReceived ? 1 : 0;
		}

		private int getLostAdder() {
			return goalsShot < goalsReceived ? 1 : 0;
		}
	}

	private void computeNewTable() {
		final Map<FootballTeam, ResultCalculator> nameToResultMapping = Maps
				.newHashMapWithExpectedSize(SeasonConstants.NUMBER_OF_TEAMS);
		for (final GameResult result : lastGameResults) {
			nameToResultMapping.put(result.getHometeam(),
					new ResultCalculator(result.getHometeamgoals(), result.getGuestteamgoals()));
			nameToResultMapping.put(result.getGuestteam(),
					new ResultCalculator(result.getGuestteamgoals(), result.getHometeamgoals()));
		}
		final List<TableEntry> newTable = Lists.newArrayListWithCapacity(SeasonConstants.NUMBER_OF_TEAMS);
		for (final TableEntry currentEntry : table) {
			final ResultCalculator result = nameToResultMapping.get(currentEntry.getTeam());
			newTable.add(createNewEntry(currentEntry, result));
		}
		sort(newTable, new Comparator<TableEntry>() {
            @Override
            public int compare(TableEntry lhs, TableEntry rhs) {
                final int pointDifference = lhs.getPoints() - rhs.getPoints();
                if (pointDifference < 0) {
                    return 1; // rhs has more points
                } else if (pointDifference > 0) {
                    return -1; // lhs has more points
                }
                final int goalDifference = lhs.getGoalDifference() - rhs.getGoalDifference();
                if (goalDifference < 0) {
                    return 1;
                }
                else if (goalDifference > 0) {
                    return -1;
                }
                return 0;
            }
        });
        table = newTable;
	}

	private TableEntry createNewEntry(TableEntry entry, ResultCalculator result) {
		final FootballTeam team = entry.getTeam();
		final int goalsShot = entry.getGoalsShot() + result.getGoalsShot();
		final int goalsReceived = entry.getGoalsReceived() + result.getGoalsReceived();
		final int won = entry.getWon() + result.getWonAdder();
		final int draw = entry.getDraw() + result.getDrawAdder();
		final int lost = entry.getLost() + result.getLostAdder();
		return new TableEntry(team, goalsShot, goalsReceived, won, draw, lost);
	}

	@Override
	public List<GameResult> getResultsForLastGameDay() {
        checkNotNull(gameSimulation);
        checkNotNull(persistency);
        checkState(lastGameDay > 0);
		return lastGameResults;
	}

	@Override
	public List<Game> getLastGameDay() {
        checkNotNull(gameSimulation);
        checkNotNull(persistency);
		checkState(lastGameDay > 0);
		return season.getGameDay(lastGameDay);
	}

	@Override
	public List<TableEntry> getTableForLastGameDay() {
        checkNotNull(gameSimulation);
        checkNotNull(persistency);
		return table;
	}

    public void setGameSimulation(GameSimulation gameSimulation) {
        checkNotNull(gameSimulation);
        this.gameSimulation = gameSimulation;
    }

    public void setPersistency(PersistencyService persistency) {
        checkNotNull(persistency);
        this.persistency = persistency;
    }
}
