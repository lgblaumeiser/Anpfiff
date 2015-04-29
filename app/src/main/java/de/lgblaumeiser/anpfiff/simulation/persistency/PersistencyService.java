/**
 * Copyright (c) 2015, Lars Geyer-Blaumeiser All rights reserved.
 *
 * See license information
 */

package de.lgblaumeiser.anpfiff.simulation.persistency;

import java.util.List;

import de.lgblaumeiser.anpfiff.simulation.model.FootballTeam;

/**
 * Required Service for persistency of the services
 *
 * @author Lars Geyer-Blaumeiser
 */
public interface PersistencyService {
	List<FootballTeam> loadInitialTeamData();
}
