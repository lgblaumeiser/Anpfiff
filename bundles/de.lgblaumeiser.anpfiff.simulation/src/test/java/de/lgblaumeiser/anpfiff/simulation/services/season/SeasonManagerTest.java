/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.season;

import static java.util.stream.Collectors.toSet;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Sets;

import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.Season;
import de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManager;

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
		final Season season = SeasonManager.getSeasonManager().newSeason();
		final Set<String> teamCombinations = Sets.newHashSetWithExpectedSize(306);
		for (int index = 0; index < 34; index++) {
			final List<Game> gameDay = season.getGameDay(index);
			final Set<String> teamNames = gameDay.stream()
					.map(game -> game.getHometeam().getName()).distinct().collect(toSet());
			teamNames.addAll(gameDay.stream().map(game -> game.getGuestteam().getName()).distinct()
					.collect(toSet()));
			assertEquals(18, teamNames.size());
			teamCombinations.addAll(gameDay.stream()
					.map(game -> game.getHometeam().getName() + game.getGuestteam().getName())
					.distinct().collect(toSet()));
		}
		assertEquals(306, teamCombinations.size());
	}
}
