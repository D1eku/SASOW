package model.util.datahandler;

import model.essentials.Environment;
import model.essentials.Experiment;
import model.essentials.Simulation;
import model.util.DataFile;
import model.util.data.MatrixData;
import model.util.data.RowData;
import model.util.datahandler.observer.IObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataHandler implements IObserver {
    private static String name_file = "default_filename_";
    private static DataHandler instance;

    private Experiment experiment;
    private Environment environment;
    private Simulation simulation;

    private MatrixData simulationData;
    private int fileNumber;

    private ArrayList<DataFile> files;


    public DataHandler(){
        simulationData = new MatrixData();
        fileNumber = 0;
    }

    public static DataHandler getInstance(){
        if(instance == null) {
            instance = new DataHandler();
        }
        return  instance;
    }

    public void writeCSVFile() {
        writeCSVFile(this.simulationData);
    }

    private void writeCSVFile(MatrixData data) {
        //Todo write csv file
        try {
            System.out.println("Starting to write the file.");
            FileWriter myWriter = new FileWriter(name_file+fileNumber+".csv");
            myWriter.write(data.getCSVFormat());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Do Write csv");
    }

    @Override
    public void update() {
        addLine();
        //En teoria se tendria que llamar
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public void setSimulation(Simulation simulation){
        this.simulation = simulation;
    }

    public void addLine(){
        RowData rd = new RowData();
        RowData rdSimulation = simulation.getData();
        RowData rdEnvironment = environment.getData();
        RowData rdAgentCountStates = environment.getCountStates();
        rd.addRows(rdSimulation);
        rd.addRows(rdEnvironment);
        rd.addRows(rdAgentCountStates);
        //System.out.println("Simulation: "+rdSimulation.toCSVFormat());
        //System.out.println("Environment: "+ rdEnvironment.toCSVFormat());
        //System.out.println("agentCountStates: "+ rdAgentCountStates.toCSVFormat());
        simulationData.addRow(rd);
    }

}
