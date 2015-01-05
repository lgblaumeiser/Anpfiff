/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents all data needed for a game
 *
 * @author Lars Geyer-Blaumeiser
 */
public class Game {
	private final FootballTeam hometeam;
	private final FootballTeam guestteam;

	/**
	 * @param hometeam
	 *            The team playing at home
	 * @param guestteam
	 *            The guest team playing in the game
	 */
	public Game(final FootballTeam hometeam, final FootballTeam guestteam) {
		checkNotNull(hometeam);
		checkNotNull(guestteam);
		this.hometeam = hometeam;
		this.guestteam = guestteam;
	}

	/**
	 * @return The team playing at home
	 */
	public final FootballTeam getHometeam() {
		return hometeam;
	}

	/**
	 * @return The team playing as guestteam
	 */
	public final FootballTeam getGuestteam() {
		return guestteam;
	}

}
