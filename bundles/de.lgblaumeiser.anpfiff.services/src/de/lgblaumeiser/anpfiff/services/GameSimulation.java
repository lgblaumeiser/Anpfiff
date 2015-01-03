/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser
 * All rights reserved.
 *
 * See license information
 */
package de.lgblaumeiser.anpfiff.services;

import de.lgblaumeiser.anpfiff.model.FootballTeam;

/**
 * This interface provides a service that allows to simulate a football game. It summarizes all necessary methods to get all necessary results
 * 
 * @author Lars Geyer-Blaumeiser
 */
public interface GameSimulation {
	GameResult simulateGame(FootballTeam hometeam, FootballTeam guestteam);
}
