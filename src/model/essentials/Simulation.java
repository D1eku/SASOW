package model.essentials;

import model.environments.twitter.EnvironmentTwitter;
import model.util.config.SimulationConfig;

public abstract class Simulation {
    protected int id;
    protected Environment environment;
    protected int NetworkSize;
    protected int SeedSize;
    protected SimulationConfig simulationConfig;
    protected int periods;

    public Simulation(int id, SimulationConfig simulationConfig){
        this.id = id;
        this.NetworkSize = simulationConfig.getNetworkSize();
        this.SeedSize = simulationConfig.getSeedSize();
        this.simulationConfig = simulationConfig;
        this.periods = simulationConfig.getPeriods();
        this.environment = new EnvironmentTwitter(id,periods, NetworkSize, SeedSize, simulationConfig.getAgentsConfigs());//Una simulacion tendra siempre un mismo ambiente
        //Por lo mismo quizas es innecesario considerar un ambiente id.
    }

    public void initialize(){
        this.environment.initialize();
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

    public abstract void run();

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
