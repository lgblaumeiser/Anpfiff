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

/**
 * @author Lars Geyer-Blaumeiser
 *
 */
public class SeasonManagerTest {

	/**
	 * Test method for
	 * {@link de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManagerImpl#newSeason()}
	 * .
	 */
	@Test
	public final void testNewSeason() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		final Set<String> teamCombinations = Sets.newHashSetWithExpectedSize(306);
		for (int currentDay = 0; currentDay < 34; currentDay++) {
			season.playNextGameDay();
			final List<Game> gameDay = season.getLastGameDay();
			final Set<String> teamNames = gameDay.stream().map(game -> game.getHometeam().getName()).distinct()
					.collect(toSet());
			teamNames.addAll(gameDay.stream().map(game -> game.getGuestteam().getName()).distinct().collect(toSet()));
			assertEquals(18, teamNames.size());
			teamCombinations.addAll(gameDay.stream()
					.map(game -> game.getHometeam().getName() + game.getGuestteam().getName()).distinct()
					.collect(toSet()));
		}
		assertEquals(306, teamCombinations.size());
	}

	@Test
	public final void testPlayNextGameDay() {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		for (int currentDay = 0; currentDay < 34; currentDay++) {
			season.playNextGameDay();
			final List<Game> gameDay = season.getLastGameDay();
			final List<GameResult> gameDayResult = season.getResultsForLastGameDay();
			for (int gameIndex = 0; gameIndex < 9; gameIndex++) {
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
}
