/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import de.lgblaumeiser.anpfiff.simulation.model.FootballTeam;
import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;
import de.lgblaumeiser.anpfiff.simulation.model.ShapeReduction;
import de.lgblaumeiser.anpfiff.simulation.persistency.PersistencyService;

/**
 * Test for the game simulation service
 *
 * @author Lars Geyer-Blaumeiser
 *
 */
public class GameSimulationTest {
	private GameSimulationImpl testee;
	private List<FootballTeam> teams;
	private final Random teamRandomizer = new Random();

	/**
	 * Creates the testee
	 */
	@Before
	public void setUp() {
		testee = new GameSimulationImpl();
		teams = PersistencyService.getPersistencyService().loadInitialTeamData();
	}

	/**
	 * Test game simulation
	 */
	@Test
	public void test() {
		final Game game = new Game(teams.get(teamRandomizer.nextInt(SeasonConstants.NUMBER_OF_TEAMS)),
				teams.get(teamRandomizer.nextInt(SeasonConstants.NUMBER_OF_TEAMS)));

		final GameResult result = testee.simulateGame(game);
		assertEquals(game.getHometeam(), result.getHometeam());
		assertEquals(game.getGuestteam(), result.getGuestteam());
		assertTrue(result.getHometeamgoals() >= 0);
		assertTrue(result.getGuestteamgoals() >= 0);
		assertTrue(result.getHometeamgoals() < 8);
		assertTrue(result.getGuestteamgoals() < 8);
	}

	private static final int NUMBER_OF_TEST_ITERATIONS = 10000;

	/**
	 * Test game simulation averages
	 */
	@Test
	public void testInNumbers() {
		int numhomegoals = 0;
		int numguestgoals = 0;
		int numhomeconditionreduction = 0;
		int numguestconditionreduction = 0;

		for (int i = 0; i < NUMBER_OF_TEST_ITERATIONS; i++) {
			final Game game = new Game(teams.get(teamRandomizer.nextInt(SeasonConstants.NUMBER_OF_TEAMS)),
					teams.get(teamRandomizer.nextInt(SeasonConstants.NUMBER_OF_TEAMS)));
			final GameResult result = testee.simulateGame(game);
			assertTrue(result.getHometeamgoals() >= 0);
			assertTrue(result.getGuestteamgoals() >= 0);
			assertTrue(result.getHometeamgoals() < 8);
			assertTrue(result.getGuestteamgoals() < 8);
			numhomegoals += result.getHometeamgoals();
			numguestgoals += result.getGuestteamgoals();
			ShapeReduction reduction = result.getHomeshapereduction();
			if (reduction.equals(ShapeReduction.LOW)) {
				numhomeconditionreduction--;
			} else if (reduction.equals(ShapeReduction.HIGH)) {
				numhomeconditionreduction++;
			}
			reduction = result.getGuestshapereduction();
			if (reduction.equals(ShapeReduction.LOW)) {
				numguestconditionreduction--;
			} else if (reduction.equals(ShapeReduction.HIGH)) {
				numguestconditionreduction++;
			}
		}

		assertTrue(numhomegoals > numguestgoals);
		assertTrue(numhomegoals / NUMBER_OF_TEST_ITERATIONS < 4);
		assertTrue(numguestgoals / NUMBER_OF_TEST_ITERATIONS < 4);
		assertTrue(numhomeconditionreduction > -1 * NUMBER_OF_TEST_ITERATIONS);
		assertTrue(numhomeconditionreduction < NUMBER_OF_TEST_ITERATIONS);
		assertTrue(numguestconditionreduction > -1 * NUMBER_OF_TEST_ITERATIONS);
		assertTrue(numguestconditionreduction < NUMBER_OF_TEST_ITERATIONS);
	}

	/**
	 * Bad parameter test
	 */
	@Test(expected = NullPointerException.class)
	public void testBadParameter() {
		testee.simulateGame(null);
	}
}
