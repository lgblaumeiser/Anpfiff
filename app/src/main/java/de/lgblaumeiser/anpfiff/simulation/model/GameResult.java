/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.hash;

/**
 * This class represents the result of a football game.
 *
 * @author Lars Geyer-Blaumeiser
 */
public class GameResult {
	private final Game game;

	private final int hometeamgoals;
	private final int guestteamgoals;

	private final ShapeReduction homeshapereduction;
	private final ShapeReduction guestshapereduction;

	/**
	 * Create a game result object
	 *
	 * @param game
	 *            The game for which this object shows the results
	 * @param hometeamgoals
	 *            The goals scored by the home team
	 * @param guestteamgoals
	 *            The goals scored by the guest team
	 * @param homeshapechange
	 *            The reduction of the home teams condition shape
	 * @param guestshapechange
	 *            The reduction of the guest teams condition shape
	 */
	public GameResult(final Game game, final int hometeamgoals, final int guestteamgoals,
			final ShapeReduction homeshapechange, final ShapeReduction guestshapechange) {
		checkNotNull(game);
		checkNotNull(homeshapechange);
		checkNotNull(guestshapechange);
		this.game = game;
		this.hometeamgoals = hometeamgoals;
		this.guestteamgoals = guestteamgoals;
		homeshapereduction = homeshapechange;
		guestshapereduction = guestshapechange;
	}

	/**
	 * @return The team played as home team
	 */
	public final FootballTeam getHometeam() {
		return game.getHometeam();
	}

	/**
	 * @return The team played as guest team
	 */
	public final FootballTeam getGuestteam() {
		return game.getGuestteam();
	}

	/**
	 * @return The goals scored by the home team
	 */
	public final int getHometeamgoals() {
		return hometeamgoals;
	}

	/**
	 * @return The goals scored by the guest team
	 */
	public final int getGuestteamgoals() {
		return guestteamgoals;
	}

	/**
	 * @return The reduction of the home team shape
	 */
	public final ShapeReduction getHomeshapereduction() {
		return homeshapereduction;
	}

	/**
	 * @return The reduction of the guest team shape
	 */
	public final ShapeReduction getGuestshapereduction() {
		return guestshapereduction;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return hash(game, hometeamgoals, guestteamgoals, homeshapereduction, guestshapereduction);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GameResult) {
			final GameResult other = (GameResult) obj;
			return equal(game, other.game) && equal(hometeamgoals, other.hometeamgoals)
					&& equal(guestteamgoals, other.guestteamgoals)
					&& equal(homeshapereduction, other.homeshapereduction)
					&& equal(guestshapereduction, other.guestshapereduction);
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
        return String.valueOf(game) + " " + hometeamgoals + " : " + guestteamgoals + " { " + homeshapereduction + ", " + guestshapereduction + " }";
	}
}
