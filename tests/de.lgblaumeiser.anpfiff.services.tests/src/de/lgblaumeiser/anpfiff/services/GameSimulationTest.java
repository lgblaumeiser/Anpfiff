/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.lgblaumeiser.anpfiff.model.FootballTeam;

/**
 * Test for the game simulation service
 *
 * @author Lars Geyer-Blaumeiser
 *
 */
public class GameSimulationTest {
	private GameSimulationImpl testee;

	/**
	 * Creates the testee
	 */
	@Before
	public void setUp() {
		testee = new GameSimulationImpl();
	}

	/**
	 * Test game simulation
	 */
	@Test
	public void test() {
		final FootballTeam hometeam = new FootballTeam();
		final FootballTeam guestteam = new FootballTeam();

		final GameResult result = testee.simulateGame(hometeam, guestteam);
		assertEquals(hometeam, result.getHometeam());
		assertEquals(guestteam, result.getGuestteam());
		assertTrue(result.getHometeamgoals() >= 0);
		assertTrue(result.getGuestteamgoals() >= 0);
		assertTrue(result.getHometeamgoals() < 8);
		assertTrue(result.getGuestteamgoals() < 8);
	}

	/**
	 * Test game simulation averages
	 */
	@Test
	public void testInNumbers() {
		int numhomegoals = 0;
		int numguestgoals = 0;
		int numhomeconditionreduction = 0;
		int numguestconditionreduction = 0;
		final FootballTeam hometeam = new FootballTeam();
		final FootballTeam guestteam = new FootballTeam();

		for (int i = 0; i < 1000; i++) {
			final GameResult result = testee.simulateGame(hometeam, guestteam);
			assertTrue(result.getHometeamgoals() >= 0);
			assertTrue(result.getGuestteamgoals() >= 0);
			assertTrue(result.getHometeamgoals() < 8);
			assertTrue(result.getGuestteamgoals() < 8);
			numhomegoals += result.getHometeamgoals();
			numguestgoals += result.getGuestteamgoals();
			numhomeconditionreduction += result.getHomeshapereduction();
			numguestconditionreduction += result.getGuestshapereduction();
		}

		assertTrue(numhomegoals > numguestgoals);
		assertTrue(numhomegoals / 1000 < 4);
		assertTrue(numguestgoals / 1000 < 4);
		assertTrue(numhomeconditionreduction / 1000.0 > 16.0);
		assertTrue(numhomeconditionreduction / 1000.0 < 20.0);
		assertTrue(numguestconditionreduction / 1000.0 > 16.0);
		assertTrue(numguestconditionreduction / 1000.0 < 20.0);
	}

	/**
	 * Bad first parameter test
	 */
	@Test(expected = NullPointerException.class)
	public void testBadParameter1() {
		testee.simulateGame(null, new FootballTeam());
	}

	/**
	 * Bad second parameter test
	 */
	@Test(expected = NullPointerException.class)
	public void testBadParameter2() {
		testee.simulateGame(new FootballTeam(), null);
	}
}
