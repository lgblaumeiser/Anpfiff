/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

/**
 * Data object representing a football team in the game
 *
 * @author Lars Geyer-Blaumeiser
 */
public class FootballTeam {
	private final int teamShape = 0;
	private final int offensiveStrength = 0;
	private final int defensiveStrength = 0;

	/**
	 *
	 * @return The conditional shape of the team, that is the sum of the shape
	 *         of the single players currently in the first 11
	 */
	public int getTeamshape() {
		return teamShape;
	}

	/**
	 *
	 * @return The strength of the offense of the team, that is the weighted sum
	 *         of the offensive strength of the players currently in the first
	 *         11
	 */
	public int getOffensiveStrength() {
		return offensiveStrength;
	}

	/**
	 * @return The strength of the defense of the team, that is the weighted sum
	 *         of defensive strength of the players currently in the first 11
	 */
	public final int getDefensiveStrength() {
		return defensiveStrength;
	}

}
