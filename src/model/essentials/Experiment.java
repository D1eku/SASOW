package model.essentials;

import model.util.config.SimulationConfig;
import model.util.data.SimulationData;

import java.util.ArrayList;

public abstract class Experiment {
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
    }

    public void run(){
        System.out.println("Starting to running in Experiment");
        for(int i = 0;i<runs; i++){
            //RunSimulation
            System.out.println("Starting run ( "+i+ ") of "+runs);
            SimulationData simulationData = simulation.run(i);
            //Add simulation to simulationArrayList;
            System.out.println("Ending run ( "+i+ ") of "+runs);
            simulationDataArrayList.add(simulationData);
        }
    }

}
