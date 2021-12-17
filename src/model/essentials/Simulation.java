package model.essentials;

import model.util.config.SimulationConfig;
import model.util.data.IDataDetailed;
import model.util.data.IDataEssential;
import model.util.data.RowData;

public abstract class Simulation implements IDataEssential, IDataDetailed {
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
        //this.environment = //Una simulacion tendra siempre un mismo ambiente

        //Por lo mismo quizas es innecesario considerar un ambiente id.
    }

    public void initialize(){
        this.environment.setSimulation(this);
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
    public RowData getDataEssential() {
        RowData rdSimulation = new RowData();
        rdSimulation.addRow(simulation_id, "simulation_id");
        rdSimulation.addRow(NetworkSize, "network_size");
        rdSimulation.addRow(SeedSize, "seed_size");
        rdSimulation.addRow(periods, "periods");
        return rdSimulation;
    }

    public RowData getDataDetailed() {
        RowData rd = new RowData();
        rd.addRow(simulation_id, "simulation_id");
        return rd;
    }
}
