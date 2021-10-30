package model.essentials;

import model.environments.twitter.SimulationTwitter;
import model.util.config.SimulationConfig;
import model.util.data.state.SimulationState;

import java.util.ArrayList;

public abstract class Experiment implements Cloneable{
    protected ArrayList<SimulationState> simulationArrayList;
    protected SimulationConfig simulation_config;
    protected Simulation simulation;
    protected int runs;
    protected String name;
    protected String description;

    public Experiment(int runs, String name, String description){
        this.runs = runs;
        this.name = name;
        this.description = description;
        this.simulationArrayList = new ArrayList<>();
        configure();
    }

    public void run() {

        System.out.println("Starting to running in Experiment");
        for(int i = 0;i<runs; i++){//Todo write line or log data when per iteration
            //RunSimulation
            System.out.println("Starting run ( "+(i)+ ") of "+(runs - 1 ));
            initialize(i);//Inicializa la simulacion.
            simulation.run();//Corre la simulacion
            //Escribir aca toda la data para un csv.
            this.simulationArrayList.add(new SimulationState(simulation));
            //Add simulation to simulationArrayList;
            System.out.println("Ending run ( "+i+ ") of "+(runs - 1 ));
        }
    }

    public void initialize(int id) {
        simulation = new SimulationTwitter(id,simulation_config);
        simulation.initialize();
    }

    public abstract void configure();

    public abstract String toString();

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
