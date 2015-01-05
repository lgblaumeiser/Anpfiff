/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.persistency;

import java.util.List;

import com.google.common.collect.Lists;

import de.lgblaumeiser.anpfiff.model.FootballTeam;

/**
 * A simple implementation of the persistency service
 *
 * @author Lars Geyer-Blaumeiser
 */
class SimplePersistencyService implements PersistencyService {
	@Override
	public List<FootballTeam> loadInitialTeamData() {
		final List<FootballTeam> teams = Lists.newArrayListWithCapacity(18);
		teams.add(new FootballTeam("Bayern München", 20, 20, 1100));
		teams.add(new FootballTeam("VfL Wolfsburg", 19, 19, 1095));
		teams.add(new FootballTeam("Bayer Leverkusen", 18, 18, 1085));
		teams.add(new FootballTeam("Borussia Mönchengladbach", 17, 19, 1085));
		teams.add(new FootballTeam("FC Schalke 04", 18, 18, 1080));
		teams.add(new FootballTeam("FC Augsburg", 16, 18, 1090));
		teams.add(new FootballTeam("1899 Hoffenheim", 18, 17, 1090));
		teams.add(new FootballTeam("Hannover 96", 17, 17, 1085));
		teams.add(new FootballTeam("Eintracht Frankfurt", 19, 16, 1085));
		teams.add(new FootballTeam("SC Paderborn 07", 17, 17, 1080));
		teams.add(new FootballTeam("1. FC Köln", 16, 18, 1080));
		teams.add(new FootballTeam("1. FSV Mainz 05", 16, 18, 1085));
		teams.add(new FootballTeam("Hertha BSC Berlin", 17, 16, 1085));
		teams.add(new FootballTeam("Hamburger SV", 15, 18, 1080));
		teams.add(new FootballTeam("VfB Stuttgart", 17, 16, 1085));
		teams.add(new FootballTeam("Werder Bremen", 18, 15, 1085));
		teams.add(new FootballTeam("Borussia Dortmund", 18, 18, 1095));
		teams.add(new FootballTeam("SC Freiburg", 16, 17, 1090));
		return teams;
	}

	private static final PersistencyService persistencyService = new SimplePersistencyService();

	static PersistencyService getInstance() {
		return persistencyService;
	}

	private SimplePersistencyService() {
		// Only internal creation to ensure singleton
	}
}
