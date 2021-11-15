package model.essentials;

import model.environments.twitter.EnvironmentTwitter;
import model.util.config.SimulationConfig;
import model.util.data.IData;
import model.util.data.RowData;

public abstract class Simulation implements IData {
    protected Experiment experiment;
    protected int simulation_id;
    protected Environment environment;
    protected int NetworkSize;
    protected int SeedSize;
    protected SimulationConfig simulationConfig;
    protected int periods;

    public Simulation(int id, SimulationConfig simulationConfig){
        this.simulation_id = id;
        this.NetworkSize = simulationConfig.getNetworkSize();
        this.SeedSize = simulationConfig.getSeedSize();
        this.simulationConfig = simulationConfig;
        this.periods = simulationConfig.getPeriods();
        this.environment = new EnvironmentTwitter(id,periods, NetworkSize, SeedSize, simulationConfig.getAgentsConfigs());//Una simulacion tendra siempre un mismo ambiente
        this.environment.setSimulation(this);
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

    public void setExperiment(Experiment exp){
        this.experiment = exp;
    }

    public Environment getEnvironment() {
        return this.environment;
    }

    public abstract void run();

    @Override
    public RowData getData() {
        RowData rdSimulation = new RowData();
        rdSimulation.addRow(simulation_id, "simulation_id");
        rdSimulation.addRow(NetworkSize, "network_size");
        rdSimulation.addRow(SeedSize, "seed_size");
        rdSimulation.addRow(periods, "periods");
        return rdSimulation;
    }
}
