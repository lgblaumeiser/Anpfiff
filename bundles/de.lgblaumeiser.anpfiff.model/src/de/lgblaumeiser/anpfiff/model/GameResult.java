/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

import static com.google.common.base.Preconditions.checkNotNull;

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
	 * @param guestteam
	 *            The team played as guest team
	 * @param hometeamgoals
	 *            The goals scored by the home team
	 * @param guestteamgoals
	 *            The goals scored by the guest team
	 * @param homeshapechange
	 *            The reduction of the home teams condition shape
	 * @param guestshapechange
	 *            The reduction of the guest teams condition shape
	 */
	public GameResult(Game game, int hometeamgoals, int guestteamgoals,
			ShapeReduction homeshapechange, ShapeReduction guestshapechange) {
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
}
