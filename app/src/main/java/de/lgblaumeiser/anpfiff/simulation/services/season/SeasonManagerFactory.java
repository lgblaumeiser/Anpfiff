package de.lgblaumeiser.anpfiff.simulation.services.season;

import de.lgblaumeiser.anpfiff.simulation.persistency.PersistencyService;
import de.lgblaumeiser.anpfiff.simulation.services.game.GameSimulation;

/**
 * Factory for creation of a new SeasonManager object which drives the simulation
 *
 * Created by lgblaumeiser on 29.04.15.
 */
public class SeasonManagerFactory {
    public static SeasonManager getSeasonManager(GameSimulation gameSimulation, PersistencyService persistency) {
        SeasonManagerImpl newSeason = new SeasonManagerImpl();
        newSeason.setGameSimulation(gameSimulation);
        newSeason.setPersistency(persistency);
        return newSeason;
    }
}
