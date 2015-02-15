/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.persistency;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import de.lgblaumeiser.anpfiff.simulation.model.FootballTeam;
import de.lgblaumeiser.anpfiff.simulation.persistency.PersistencyService;

/**
 * Tests for the persistency service
 *
 * @author Lars Geyer-Blaumeiser
 */
public class PersistencyServiceTest {
	/**
	 * Test the loadInitialTeamData method
	 */
	@Test
	public void testLoadInitialTeamData() {
		final List<FootballTeam> teams = PersistencyService.getPersistencyService()
				.loadInitialTeamData();

		assertEquals(18, teams.size());
		for (final FootballTeam current : teams) {
			assertTrue(current.getDefensiveStrength() > 0);
			assertTrue(current.getOffensiveStrength() > 0);
			assertTrue(current.getTeamshape() > 0);
			assertNotNull(current.getName());
		}
	}

}
