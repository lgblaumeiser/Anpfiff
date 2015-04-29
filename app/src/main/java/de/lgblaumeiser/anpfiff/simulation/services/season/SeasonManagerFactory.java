package de.lgblaumeiser.anpfiff.simulation.services.season;

/**
 * Created by lars on 29.04.15.
 */
public class SeasonManagerFactory {
    public static SeasonManager getSeasonManager() {
        return new SeasonManagerImpl();
    }
}
