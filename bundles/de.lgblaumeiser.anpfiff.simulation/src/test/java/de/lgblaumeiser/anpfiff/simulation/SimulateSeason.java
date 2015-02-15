/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation;

import de.lgblaumeiser.anpfiff.simulation.model.GameResult;
import de.lgblaumeiser.anpfiff.simulation.model.SeasonConstants;
import de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManager;

/**
 * Test class to manually simulate a season and print the results
 *
 * @author Lars Geyer-Blaumeiser
 */
public class SimulateSeason {

	/**
	 * @param args
	 *            Command line parameters (not used)
	 */
	public static void main(String[] args) {
		final SeasonManager season = SeasonManager.getSeasonManager().newSeason();
		for (int gameday = 0; gameday < SeasonConstants.NUMBER_OF_GAME_DAYS; gameday++) {
			season.playNextGameDay();
			System.out.println("Gameday " + (gameday + 1) + ":");
			for (final GameResult game : season.getResultsForLastGameDay()) {
				System.out.println(game);
			}
			System.out.println(" ");
		}
	}
}
