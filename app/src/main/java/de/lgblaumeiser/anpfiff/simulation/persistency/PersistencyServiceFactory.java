package de.lgblaumeiser.anpfiff.simulation.persistency;

/**
 * Created by lars on 29.04.15.
 */
public class PersistencyServiceFactory {
    public static PersistencyService getPersistencyService() {
        return new SimplePersistencyService();
    }
}
