package model.environments.facebook;

import model.essentials.Simulation;
import model.util.config.SimulationConfig;

public class SimulationFacebook extends Simulation {
    public SimulationFacebook(int id, SimulationConfig simulationConfig) {
        super(id, simulationConfig);
    }

    @Override
    public void run() {
        System.out.println("Starting Run in Simulation");
        environment.run();
        System.out.println("Ending Run in Simulation");
    }
}
