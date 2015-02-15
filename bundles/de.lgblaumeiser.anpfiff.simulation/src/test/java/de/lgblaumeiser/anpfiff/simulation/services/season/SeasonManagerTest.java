/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.season;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;

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
}
