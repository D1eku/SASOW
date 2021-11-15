package model.essentials;

import model.environments.twitter.SimulationTwitter;
import model.util.datahandler.DataHandler;
import model.util.data.IData;
import model.util.datahandler.observer.IObservable;
import model.util.config.SimulationConfig;
import model.util.data.RowData;

public abstract class Experiment implements IObservable, IData {
    protected DataHandler dataHandler;
    protected SimulationConfig simulation_config;
    protected Simulation simulation;
    protected int runs;
    protected int run;
    protected String name;
    protected String description;

    public Experiment(int runs, String name, String description){
        this.runs = runs;
        this.name = name;
        this.description = description;
        this.dataHandler = DataHandler.getInstance();
        configure();
    }

    public void run() {
        System.out.println("Starting to running in Experiment");
        while(run < runs) {
            System.out.println("Starting run ( "+(run)+ ") of "+(runs - 1 ));
            initialize(run);
            simulation.run();
            setRun(++run);
            System.out.println("Ending run ( "+run+ ") of "+(runs - 1 ));
        }

        dataHandler.writeCSVFile();
    }

    public void initialize(int id) {
        simulation = new SimulationTwitter(id,simulation_config);
        simulation.setExperiment(this);
        simulation.initialize();
        this.dataHandler.setExperiment(this);
        this.dataHandler.setSimulation(simulation);
        this.dataHandler.setEnvironment(simulation.getEnvironment());
    }

    @Override
    public RowData getData(){
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
        this.dataHandler.update();
    }
}
