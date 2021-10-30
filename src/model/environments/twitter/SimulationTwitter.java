package model.environments.twitter;

import model.essentials.Simulation;
import model.util.config.SimulationConfig;

public class SimulationTwitter extends Simulation {

    public SimulationTwitter(int id,SimulationConfig simulationConfig) {
        super(id, simulationConfig);
    }

    @Override
    public void run() {
        System.out.println("Starting Run in Simulation");
        environment.run();
        System.out.println("Ending Run in Simulation");
    }

}
