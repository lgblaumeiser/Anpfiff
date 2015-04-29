package de.lgblaumeiser.anpfiff.simulation.services.game;

/**
 * Created by lars on 29.04.15.
 */
public class GameSimulationFactory {
    public static GameSimulation getGameSimulation() {
        return new GameSimulationImpl();
    }
}
