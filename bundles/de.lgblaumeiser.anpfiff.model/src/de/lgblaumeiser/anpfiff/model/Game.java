/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.hash;

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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return hash(hometeam, guestteam);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Game) {
			final Game other = (Game) obj;
			return equal(hometeam, other.hometeam) && equal(guestteam, other.guestteam);
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return hometeam.getName() + " : " + guestteam.getName();
	}

}
