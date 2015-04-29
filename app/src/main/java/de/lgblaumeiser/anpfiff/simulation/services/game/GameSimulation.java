/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */
package de.lgblaumeiser.anpfiff.simulation.services.game;

import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;

/**
 * This interface provides a service that allows to simulate a football game. It
 * summarizes all necessary methods to get all necessary results
 *
 * @author Lars Geyer-Blaumeiser
 */
public interface GameSimulation {
	/**
	 * Simulate a game based on the team data referenced in the given game
	 * metadata
	 *
	 * @param game
	 *            Game to simulate
	 * @return The game result object which represents the results
	 */
	GameResult simulateGame(Game game);
}
