package model.essentials;

import model.environments.twitter.SimulationTwitter;
import model.util.config.DataHandlerConfig;
import model.util.config.ExperimentConfig;
import model.util.config.SimulationConfig;
import model.util.data.IDataEssential;
import model.util.data.RowData;
import model.util.datahandler.DataHandler;
import model.util.datahandler.observer.IObservable;

public abstract class Experiment implements IObservable, IDataEssential {
    protected DataHandler dataHandler;
    protected SimulationConfig simulationConfig;
    protected Simulation simulation;
    protected int runs;
    protected int run;
    protected String name;
    protected String description;
    protected ExperimentConfig experimentConfig;

    public Experiment(int runs, String name, String description, DataHandlerConfig dataHandlerConfig){
        this.runs = runs;
        this.name = name;
        this.description = description;
        this.dataHandler = DataHandler.getInstance();
        this.dataHandler.setDataHandlerConfig(dataHandlerConfig);
        configure();
        configureExperimentConfig();
        this.dataHandler.setExperimentConfig(experimentConfig);
    }

    public void run() {
        System.out.println("Starting to running in Experiment");
        while(run < runs) {
            System.out.println("Starting run ( "+(run)+ ") of "+(runs ));
            initialize(run);
            simulation.run();
            setRun(++run);
            System.out.println("Ending run ( "+run+ ") of "+(runs  ));
        }

        dataHandler.writeCSVFile();
    }

    public void initialize(int id) {
        simulation = new SimulationTwitter(id, simulationConfig);
        simulation.setExperiment(this);
        simulation.initialize();
        this.dataHandler.setExperiment(this);
        this.dataHandler.setSimulation(simulation);
        this.dataHandler.setEnvironment(simulation.getEnvironment());
    }

    @Override
    public RowData getDataEssential(){
        RowData rd = new RowData();
        rd.addRow(name, "experiment_name");
        rd.addRow(description, "experiment_description");
        rd.addRow(runs, "experiment_runs");
        rd.addRow(run, "experiment_run");
        return  rd;
    }

    public void setRun(int run) {
        this.run = run;
        notifyData();
    }

    public abstract void configure();

    @Override
    public void notifyData() {
        this.dataHandler.updateEssential();
    }

    public DataHandler getDataHandler() {
        return dataHandler;
    }

    private void configureExperimentConfig() {
        experimentConfig = new ExperimentConfig(simulationConfig);
        experimentConfig.setName(name);
        experimentConfig.setDescription(description);
        experimentConfig.setRepetitions(runs);
        experimentConfig.setEssentialData(dataHandler.getDataHandlerConfig().isEssentialData());
        experimentConfig.setDetailedData(dataHandler.getDataHandlerConfig().isDetailedData());
    }
}
