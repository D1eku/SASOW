package model.essentials;

import model.environments.twitter.SimulationTwitter;
import model.util.DataHandler;
import model.util.observer.IObservable;
import model.util.config.SimulationConfig;
import model.util.data.RowData;

public abstract class Experiment implements IObservable {
    //protected ArrayList<Object> simulationArrayList;
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
        setRun(0);
        while(run < runs) {
            System.out.println("Starting run ( "+(run)+ ") of "+(runs - 1 ));
            initialize(run);
            simulation.run();
            setRun(++run);
            System.out.println("Ending run ( "+run+ ") of "+(runs - 1 ));
        }
    }

    public void initialize(int id) {
        simulation = new SimulationTwitter(id,simulation_config);
        simulation.setExperiment(this);
        simulation.initialize();
        this.dataHandler.setEnvironment(this.simulation.getEnvironment());
    }

    public RowData getData(){
        RowData rd = new RowData(name);
        rd.addRow(description);
        rd.addRow(runs);
        rd.addRow(run);
        return  rd;
    }

    public void setRun(int run) {
        System.out.println("PASO ESTO ? ");
        this.run = run;
        notify(0);
    }

    public abstract void configure();

    @Override
    public void notify(int change) {
        this.dataHandler.update(change);
    }
}
