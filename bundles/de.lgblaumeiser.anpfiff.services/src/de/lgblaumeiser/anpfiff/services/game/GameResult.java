/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.services.game;

import de.lgblaumeiser.anpfiff.model.FootballTeam;

/**
 * This class represents the result of a football game.
 *
 * @author Lars Geyer-Blaumeiser
 */
public class GameResult {
	private final FootballTeam hometeam;
	private final FootballTeam guestteam;

	private final int hometeamgoals;
	private final int guestteamgoals;

	private final int homeshapereduction;
	private final int guestshapereduction;

	/**
	 * Create a game result object
	 *
	 * @param hometeam
	 *            The team played as home team
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
	GameResult(FootballTeam hometeam, FootballTeam guestteam, int hometeamgoals,
			int guestteamgoals, int homeshapechange, int guestshapechange) {
		this.hometeam = hometeam;
		this.guestteam = guestteam;
		this.hometeamgoals = hometeamgoals;
		this.guestteamgoals = guestteamgoals;
		homeshapereduction = homeshapechange;
		guestshapereduction = guestshapechange;
	}

	/**
	 * @return The team played as home team
	 */
	public final FootballTeam getHometeam() {
		return hometeam;
	}

	/**
	 * @return The team played as guest team
	 */
	public final FootballTeam getGuestteam() {
		return guestteam;
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
	public final int getHomeshapereduction() {
		return homeshapereduction;
	}

	/**
	 * @return The reduction of the guest team shape
	 */
	public final int getGuestshapereduction() {
		return guestshapereduction;
	}
}
