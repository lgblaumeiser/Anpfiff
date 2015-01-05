/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.services.season;

import de.lgblaumeiser.anpfiff.model.Season;

/**
 * Service provides methods for game control like starting a game, persistency,
 * ...
 *
 * @author Lars Geyer-Blaumeiser
 */
public interface SeasonManager {
	/**
	 * Creates a new season, i.e., reads in the team data and initializes all
	 * season data
	 *
	 * @return The general management object for a season which is the starting
	 *         point for the model
	 */
	Season newSeason();

	static SeasonManager getSeasonManager() {
		return SeasonManagerImpl.getInstance();
	}
}
