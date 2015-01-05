/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */
package de.lgblaumeiser.anpfiff.services.game;

import de.lgblaumeiser.anpfiff.model.Game;
import de.lgblaumeiser.anpfiff.model.GameResult;

/**
 * This interface provides a service that allows to simulate a football game. It
 * summarizes all necessary methods to get all necessary results
 *
 * @author Lars Geyer-Blaumeiser
 */
public interface GameSimulation {
	GameResult simulateGame(Game game);
}
