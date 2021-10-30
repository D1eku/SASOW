package model.util.data.state;

import model.essentials.Simulation;

public class SimulationState {

    private Simulation simulation;

    public SimulationState(Simulation simulation) throws CloneNotSupportedException {
        simulation = (Simulation) simulation.clone();
    }
}
