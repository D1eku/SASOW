package model.environments.twitter;

import model.essentials.Simulation;
import model.util.config.SimulationConfig;
import model.util.data.EnvironmentInfo;
import model.util.data.SimulationData;

public class SimulationTwitter extends Simulation {

    public SimulationTwitter(int id,SimulationConfig simulationConfig) {
        super(id, simulationConfig);
    }

    @Override
    public SimulationData run() {
        System.out.println("Starting Run in Simulation");
        EnvironmentInfo ei = environment.run();
        return new SimulationData(ei);
    }

    @Override
    public String toString() {
        return "SIMULATION TWITTER";
    }

}
