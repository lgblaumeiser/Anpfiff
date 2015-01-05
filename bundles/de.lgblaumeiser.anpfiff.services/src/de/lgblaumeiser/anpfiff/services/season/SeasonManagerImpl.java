/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.services.season;

import java.util.List;

import com.google.common.collect.Lists;

import de.lgblaumeiser.anpfiff.model.FootballTeam;
import de.lgblaumeiser.anpfiff.model.Game;
import de.lgblaumeiser.anpfiff.model.GamePlan;
import de.lgblaumeiser.anpfiff.model.Season;
import de.lgblaumeiser.anpfiff.persistency.PersistencyService;

/**
 * Implementation of the SeasonManager service interface
 *
 * @author Lars Geyer-Blaumeiser
 */
class SeasonManagerImpl implements SeasonManager {
	private final PersistencyService persistency = PersistencyService.getPersistencyService();

	/*
	 * (non-Javadoc)
	 *
	 * @see de.lgblaumeiser.anpfiff.services.season.SeasonManager#newSeason()
	 */
	@Override
	public Season newSeason() {
		final List<FootballTeam> teams = persistency.loadInitialTeamData();
		final GamePlan gamePlan = createGamePlan(teams);
		return new Season(teams, gamePlan);
	}

	private GamePlan createGamePlan(final List<FootballTeam> teams) {
		final List<Game> gameday = Lists.newArrayListWithCapacity(9);
		for (int index = 0; index < 18; index += 2) {
			gameday.add(new Game(teams.get(index), teams.get(index + 1)));
		}
		final List<List<Game>> gameDays = Lists.newArrayListWithCapacity(34);
		gameDays.add(gameday);
		return new GamePlan(gameDays);
	}

	private static final SeasonManager seasonManager = new SeasonManagerImpl();

	public static SeasonManager getInstance() {
		return seasonManager;
	}

	private SeasonManagerImpl() {
		// Ensure Singleton behavior
	}
}
