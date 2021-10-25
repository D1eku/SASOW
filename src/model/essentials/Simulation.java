package model.essentials;

import model.util.config.SimulationConfig;
import model.util.data.SimulationData;

public abstract class Simulation {
    protected Environment environment;
    protected int NetworkSize;
    protected int SeedSize;
    protected SimulationConfig simulationConfig;
    protected int periods;

    public Simulation(SimulationConfig simulationConfig){
        this.NetworkSize = simulationConfig.getNetworkSize();
        this.SeedSize = simulationConfig.getSeedSize();
        this.simulationConfig = simulationConfig;
        this.periods = simulationConfig.getPeriods();
        //this.environment = env;
    }

    public int getNetworkSize() {
        return this.simulationConfig.getNetworkSize();
    }

    public int getSeedSize() {
        return  this.simulationConfig.getSeedSize();
    }

    public SimulationConfig getSimulationConfig() {
        return this.simulationConfig;
    }

    public void setSimulationConfig(SimulationConfig simulationConfig) {
        this.simulationConfig = simulationConfig;
    }

    public abstract SimulationData run(int run);
}
