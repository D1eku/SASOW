package model.essentials;

import model.environments.twitter.SimulationTwitter;
import model.util.config.SimulationConfig;
import model.util.data.SimulationData;

import java.util.ArrayList;

public abstract class Experiment implements Cloneable{
    protected ArrayList<Simulation> simulationArrayList;
    protected ArrayList<SimulationData> simulationDataArrayList;
    protected SimulationConfig simulation_config;
    protected Simulation simulation;
    protected int runs;
    protected String name;
    protected String description;

    public Experiment(int runs, String name, String description){
        this.runs = runs;
        this.name = name;
        this.description = description;
        this.simulationDataArrayList = new ArrayList<>();
        this.simulationArrayList = new ArrayList<>();
        configure();
    }

    public void run(){

        System.out.println("Starting to running in Experiment");
        for(int i = 0;i<runs; i++){//Todo write line or log data when per iteration
            //RunSimulation
            System.out.println("Starting run ( "+(i)+ ") of "+(runs - 1 ));
            initialize(i);//Inicializa la simulacion.
            SimulationData simulationData = simulation.run();//Corre la simulacion
            //Escribir aca toda la data para un csv.

            //Add simulation to simulationArrayList;
            System.out.println("Ending run ( "+i+ ") of "+(runs - 1 ));
            simulationDataArrayList.add(simulationData);
        }
    }

    public void initialize(int id) {
        simulation = new SimulationTwitter(id,simulation_config);
        simulation.initialize();
    }

    public abstract void configure();

    //    public abstract String toString();

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

}
