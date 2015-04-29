/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.model;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Objects.hash;

/**
 * Central starting point of a game model.
 *
 * @author Lars Geyer-Blaumeiser
 */
public class Season {
	private final List<FootballTeam> teams;
	private final GamePlan gamePlan;

	public Season(final List<FootballTeam> teams, final GamePlan gamePlan) {
		checkNotNull(teams);
		checkArgument(teams.size() == SeasonConstants.NUMBER_OF_TEAMS);
		checkNotNull(gamePlan);
		this.teams = teams;
		this.gamePlan = gamePlan;
	}

	public List<Game> getGameDay(final int index) {
		return gamePlan.getGameDay(index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return hash(teams, gamePlan);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Season) {
			final Season other = (Season) obj;
			return teams.equals(other.teams) && gamePlan.equals(other.gamePlan);
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
		final StringBuilder seasonString = new StringBuilder(10000);
		seasonString.append("Teams:\n");
		for (final FootballTeam currentTeam : teams) {
			seasonString.append(currentTeam);
			seasonString.append("\n");
		}
		seasonString.append("\n");
		seasonString.append(gamePlan);
		return seasonString.toString();
	}
}
