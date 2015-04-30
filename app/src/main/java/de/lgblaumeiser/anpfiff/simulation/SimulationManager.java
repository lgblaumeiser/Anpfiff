package de.lgblaumeiser.anpfiff.simulation;

import de.lgblaumeiser.anpfiff.simulation.persistency.PersistencyServiceFactory;
import de.lgblaumeiser.anpfiff.simulation.services.game.GameSimulationFactory;
import de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManagerFactory;
import de.lgblaumeiser.anpfiff.simulation.services.season.SeasonManager;

/**
 * SimulationManager is an initializer which creates the needed objects and returns the entry point for the simulation
 *
 * created by lgblaumeiser on 30.04.2015
 */
public class SimulationManager {

    public SeasonManager simulation() {
        return SeasonManagerFactory.getSeasonManager(GameSimulationFactory.getGameSimulation(), PersistencyServiceFactory.getPersistencyService());
    }
}
