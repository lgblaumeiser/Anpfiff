/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.season;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.google.common.collect.Sets;

import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;
import de.lgblaumeiser.anpfiff.simulation.model.TableEntry;

/**
 * @author Lars Geyer-Blaumeiser
 *
 */
public class SeasonManagerTest {
	private static final int NUMBER_OF_GAME_COMBINATIONS_PER_SEASON = 306;

	/**
	 * Test method for
	 * {@link de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManagerImpl#newSeason()}
	 * .
	 */
	@Test
	public final void testNewSeason() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		final Set<String> teamCombinations = Sets.newHashSetWithExpectedSize(NUMBER_OF_GAME_COMBINATIONS_PER_SEASON);
		for (int currentDay = 0; currentDay < SeasonConstants.NUMBER_OF_GAME_DAYS; currentDay++) {
			season.playNextGameDay();
			final List<Game> gameDay = season.getLastGameDay();
			final Set<String> teamNames = gameDay.stream().map(game -> game.getHometeam().getName()).distinct()
					.collect(toSet());
			teamNames.addAll(gameDay.stream().map(game -> game.getGuestteam().getName()).distinct().collect(toSet()));
			assertEquals(SeasonConstants.NUMBER_OF_TEAMS, teamNames.size());
			teamCombinations.addAll(gameDay.stream()
					.map(game -> game.getHometeam().getName() + game.getGuestteam().getName()).distinct()
					.collect(toSet()));
		}
		assertEquals(306, teamCombinations.size());
	}

	@Test
	public final void testPlayNextGameDay() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		for (int currentDay = 0; currentDay < SeasonConstants.NUMBER_OF_GAME_DAYS; currentDay++) {
			season.playNextGameDay();
			final List<Game> gameDay = season.getLastGameDay();
			final List<GameResult> gameDayResult = season.getResultsForLastGameDay();
			for (int gameIndex = 0; gameIndex < SeasonConstants.NUMBER_OF_GAMES_PER_DAY; gameIndex++) {
				final Game currentGame = gameDay.get(gameIndex);
				final GameResult currentResult = gameDayResult.get(gameIndex);
				assertEquals(currentGame.getHometeam(), currentResult.getHometeam());
				assertEquals(currentGame.getGuestteam(), currentResult.getGuestteam());
				assertTrue(currentResult.getHometeamgoals() >= 0);
				assertTrue(currentResult.getGuestteamgoals() >= 0);
				assertTrue(currentResult.getHometeamgoals() < 8);
				assertTrue(currentResult.getGuestteamgoals() < 8);

			}
		}
	}

	@Test(expected = IllegalStateException.class)
	public final void testLastGameDayNoGameDayPlayed() {
		SeasonManager.getSeasonManager().newSeason().getLastGameDay();
	}

	@Test(expected = IllegalStateException.class)
	public final void testLastGameDayResultNoGameDayPlayed() {
		SeasonManager.getSeasonManager().newSeason().getResultsForLastGameDay();
	}

	@Test(expected = IllegalStateException.class)
	public final void testPlayNextGameDayMoreThanCorrectNumberOfTimes() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		for (int index = 0; index < SeasonConstants.NUMBER_OF_GAME_DAYS + 1; index++) {
			season.playNextGameDay();
		}
	}

	private static final int TEST_ITERATIONS = 1000;

	@Test
	public final void testPlaySeasonDiversion() {
		final Set<String> seasonStrings = Sets.newHashSetWithExpectedSize(TEST_ITERATIONS);
		int duplicatedSeasons = 0;
		for (int index = 0; index < 1000; index++) {
			final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
			String seasonString = "";
			for (int gameday = 0; gameday < SeasonConstants.NUMBER_OF_GAME_DAYS; gameday++) {
				season.playNextGameDay();
				final List<Game> gameDay = season.getLastGameDay();
				seasonString = seasonString
						+ gameDay.stream().map(game -> game.getHometeam().getName() + game.getGuestteam().getName())
								.collect(Collectors.joining());
			}
			if (seasonStrings.contains(seasonString)) {
				duplicatedSeasons++;
			}
			seasonStrings.add(seasonString);
		}
		assertEquals(0, duplicatedSeasons);
	}

	@Test
	public void testTable() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		int pointsGatheredFromResults = 0;
		int goalsGatheredFromResults = 0;
		for (int currentDay = 0; currentDay < SeasonConstants.NUMBER_OF_GAME_DAYS; currentDay++) {
			final Set<String> teamNames = Sets.newHashSetWithExpectedSize(SeasonConstants.NUMBER_OF_TEAMS);
			int pointsGatheredFromTable = 0;
			int goalsShotGatheredFromTable = 0;
			int goalsReceivedGatheredFromTable = 0;
			season.playNextGameDay();
			final List<GameResult> gameDayResult = season.getResultsForLastGameDay();
			pointsGatheredFromResults += gameDayResult.stream()
					.map(gameResult -> gameResult.getHometeamgoals() == gameResult.getGuestteamgoals() ? 2 : 3)
					.mapToInt(Integer::intValue).sum();
			goalsGatheredFromResults += gameDayResult.stream()
					.map(gameResult -> gameResult.getHometeamgoals() + gameResult.getGuestteamgoals())
					.mapToInt(Integer::intValue).sum();
			final List<TableEntry> table = season.getTableForLastGameDay();
			for (final TableEntry tableEntry : table) {
				teamNames.add(tableEntry.getTeam().getName());
				pointsGatheredFromTable += tableEntry.getPoints();
				goalsShotGatheredFromTable += tableEntry.getGoalsShot();
				goalsReceivedGatheredFromTable += tableEntry.getGoalsReceived();
			}
			assertEquals(pointsGatheredFromResults, pointsGatheredFromTable);
			assertEquals(goalsGatheredFromResults, goalsShotGatheredFromTable);
			assertEquals(goalsGatheredFromResults, goalsReceivedGatheredFromTable);
			assertEquals(SeasonConstants.NUMBER_OF_TEAMS, teamNames.size());
		}
	}

	@Test
	public void testSequenceInTable() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		for (int gameday = 0; gameday < SeasonConstants.NUMBER_OF_GAME_DAYS; gameday++) {
			season.playNextGameDay();
			final Iterator<TableEntry> iterator = season.getTableForLastGameDay().iterator();
			TableEntry last = iterator.next();
			while (iterator.hasNext()) {
				final TableEntry current = iterator.next();
				assertFalse(last.getPoints() < current.getPoints());
				assertFalse(last.getPoints() == current.getPoints()
						&& last.getGoalDifference() < current.getGoalDifference());
				last = current;
			}
		}
	}
}
