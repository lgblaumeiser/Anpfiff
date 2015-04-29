/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

/**
 * Interface defines general constants to be used for specific numbers
 *
 * @author Lars Geyer-Blaumeiser
 */
public interface SeasonConstants {
	/**
	 * Number of teams in league
	 */
	final static int NUMBER_OF_TEAMS = 18;

	/**
	 * Number of game days per half season
	 */
	final static int NUMBER_OF_GAME_DAYS_PER_HALF_SEASON = NUMBER_OF_TEAMS - 1;

	/**
	 * Number of game days per season
	 */
	final static int NUMBER_OF_GAME_DAYS = NUMBER_OF_GAME_DAYS_PER_HALF_SEASON * 2;

	/**
	 * Number of games per game day
	 */
	final static int NUMBER_OF_GAMES_PER_DAY = NUMBER_OF_TEAMS / 2;

	/**
	 * Break after half season (winter break)
	 */
	final static boolean WINTER_BREAK_AFTER_HALF_SEASON = true;
}
