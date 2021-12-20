package experiments.main.Manuela;

import model.essentials.Experiment;
import model.util.config.DataHandlerConfig;

public class ExperimentManuela extends Experiment {
    private int saturationThreshHold;
    private int maxResends;

    public ExperimentManuela(int runs, String name, String description, DataHandlerConfig dataHandlerConfig, int saturationThreshHold, int maxResends) {
        super(runs, name, description, dataHandlerConfig);
        this.maxResends=maxResends;
        this.saturationThreshHold = saturationThreshHold;
    }

    @Override
    public void initialize(int id) {
        simulation = new SimulationManuela(id, simulationConfig, saturationThreshHold, maxResends);
        simulation.setExperiment(this);
        simulation.initialize();
        this.dataHandler.setExperiment(this);
        this.dataHandler.setSimulation(simulation);
        this.dataHandler.setEnvironment(simulation.getEnvironment());
    }

    @Override
    public void configure() {

    }
}
