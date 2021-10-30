package model.util;

import model.essentials.Environment;
import model.essentials.Experiment;
import model.util.data.ColumnData;
import model.util.data.RowData;
import model.util.observer.IObserver;

import java.io.FileWriter;
import java.io.IOException;

public class DataHandler implements IObserver {
    // Opciones para el update
    public static final int WRITE_FILE = 0;
    public static final int PERIOD_UPDATE = 1;

    // Niveles de escritura de archivos
    public static final int FULL_DATA = 1;//Informacion de configuracion, y conteo de estados por simulacion y por periodo.
    public static final int DETAILED_AGENT_DATA = 2;//Lo anterior + la informacion de cada agente por cada periodo para cada simulacion.

    //Variables de configuracion
    private static int level = FULL_DATA;
    private static String name_file = "default_filename_";
    private static DataHandler instance;
    //
    private Experiment e;
    private ColumnData simulationData;
    private ColumnData agents_detailed_data;
    private int fileNumber;


    public DataHandler(String name_file, int level){
        simulationData = new ColumnData();
        fileNumber = 0;
        DataHandler.level = level;
        DataHandler.name_file = name_file;

        switch (level){
            case FULL_DATA:
                simulationData = new ColumnData();
                break;
            case DETAILED_AGENT_DATA:
                 simulationData = new ColumnData();
                 agents_detailed_data = new ColumnData();
                 break;
        }
    }

    public DataHandler(){
        simulationData = new ColumnData();
        fileNumber = 0;
    }

    public static DataHandler getInstance(){
        if(instance == null) {
            instance = new DataHandler(name_file, level);
        }
        return  instance;
    }

    //Todo write XLSX file
    public void writeCSVFile(int configuration) {
        writeCSVFile();
    }

    private void writeCSVFile() {
        //Todo write csv file
        try {
            System.out.println("Starting to write the file.");
            FileWriter myWriter = new FileWriter(name_file+fileNumber+".csv");
            myWriter.write(simulationData.getCSVFormat());
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("Do Write csv");
    }

    @Override
    public void update(int type) {
        switch (type){
            case WRITE_FILE:
                this.writeCSVFile();
                fileNumber++;
                break;
            case PERIOD_UPDATE:
                generateData();
                break;
            default:
                System.out.println("error brigido");
                break;
        }

    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void addRow(RowData row) {
        simulationData.addRow(row);
    }

    public void generateData(){
        switch (level) {
            case FULL_DATA -> generateByFullData();
            case DETAILED_AGENT_DATA -> generateDetailedData();
        }
    }

    private void generateByFullData() {
        this.environment.getData();
    }

    private void generateDetailedData(){

    }


}
