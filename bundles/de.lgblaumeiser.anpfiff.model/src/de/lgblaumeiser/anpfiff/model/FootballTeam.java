/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data object representing a football team in the game
 *
 * @author Lars Geyer-Blaumeiser
 */
public class FootballTeam {
	private final int teamShape;
	private final int offensiveStrength;
	private final int defensiveStrength;
	private final String name;

	/**
	 * @param name
	 *            Name of the team
	 * @param offensiveStrength
	 *            The offensive strength of the team
	 * @param defensiveStrength
	 *            The defensive strength of the team
	 * @param teamShape
	 *            The condition shape of the team
	 */
	public FootballTeam(final String name, final int offensiveStrength,
			final int defensiveStrength, final int teamShape) {
		checkNotNull(name);
		checkArgument(offensiveStrength > 0);
		checkArgument(defensiveStrength > 0);
		checkArgument(teamShape > 0);
		this.teamShape = teamShape;
		this.offensiveStrength = offensiveStrength;
		this.defensiveStrength = defensiveStrength;
		this.name = name;
	}

	/**
	 * @return The conditional shape of the team, that is the sum of the shape
	 *         of the single players currently in the first 11
	 */
	public int getTeamshape() {
		return teamShape;
	}

	/**
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

	/**
	 * @return The name of the team
	 */
	public String getName() {
		return name;
	}
}
