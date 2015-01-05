/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

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
		checkArgument(teams.size() == 18);
		checkNotNull(gamePlan);
		this.teams = teams;
		this.gamePlan = gamePlan;
	}

	public List<Game> getGameDay(final int index) {
		return gamePlan.getGameDay(index);
	}
}
