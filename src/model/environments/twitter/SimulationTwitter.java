package model.environments.twitter;

import model.essentials.Simulation;
import model.util.config.SimulationConfig;
import model.util.data.SimulationData;

public class SimulationTwitter extends Simulation {

    public SimulationTwitter(SimulationConfig simulationConfig) {
        super(simulationConfig);
        this.environment = new EnvironmentTwitter(this.periods, NetworkSize, SeedSize, simulationConfig.getAgentsConfigs());
    }



    @Override
    public SimulationData run(int run) {
        System.out.println("Starting Run in Simulation");
        this.environment.initialize();
        return new SimulationData(this.environment.run());
    }

}
