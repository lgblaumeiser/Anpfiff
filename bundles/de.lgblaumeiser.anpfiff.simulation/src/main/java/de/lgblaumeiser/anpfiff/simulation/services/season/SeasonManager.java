/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.services.season;

import java.util.List;

import de.lgblaumeiser.anpfiff.simulation.model.Game;
import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.Table;

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
	 * @return The season manager to allow a fluent interface
	 */
	SeasonManager newSeason();

	/**
	 * Play the next game day
	 *
	 * @return The season manager itself to allow a fluent interface
	 */
	SeasonManager playNextGameDay();

	/**
	 * @return Returns the results of the last game day
	 */
	List<GameResult> getResultsForLastGameDay();

	/**
	 * @return Returns the games of the last game day
	 */
	List<Game> getLastGameDay();

	/**
	 * @return Returns the table after the last game day
	 */
	Table getTableForLastGameDay();

	/**
	 * @return Access to a new season manager
	 */
	static SeasonManager getSeasonManager() {
		return new SeasonManagerImpl();
	}
}
