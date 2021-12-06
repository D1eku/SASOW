package model.util.datahandler;

import GUI.MainFrame;
import model.essentials.Agent;
import model.essentials.Environment;
import model.essentials.Experiment;
import model.essentials.Simulation;
import model.util.config.DataHandlerConfig;
import model.util.config.ExperimentConfig;
import model.util.data.MatrixData;
import model.util.data.RowData;
import model.util.datahandler.observer.IObserver;

import java.io.FileWriter;

public class DataHandler implements IObserver {
    private static DataHandler instance;

    //Vars to access after to get data to write ._.
    private Experiment experiment;
    private Environment environment;
    private Simulation simulation;
    private ExperimentConfig experimentConfig;

    private MatrixData essentialData;
    private MatrixData detailedData;

    private boolean withInterface = false;

    private DataHandlerConfig dataHandlerConfig;

    /* Constructor */
    public DataHandler(){
        essentialData = new MatrixData();
        detailedData = new MatrixData();
    }

    public static DataHandler getInstance(){
        if(instance == null) {
            instance = new DataHandler();
        }
        return  instance;
    }

    public static void clearInstance() {
        instance = null;
    }

    /* Update by interfaces and util  */

    @Override
    public void updateEssential() {
        if(dataHandlerConfig.isEssentialData()){
            addLineEssential();
        }
    }

    @Override
    public void updateDetailed(Agent a) {
        if(dataHandlerConfig.isDetailedData()){
            addLineDetailed(a);
        }
    }


    public void addLineEssential(){
        RowData rd = new RowData();
        RowData rdSimulation = simulation.getDataEssential();
        RowData rdEnvironment = environment.getDataEssential();
        //RowData rdAgentCountStates = environment.getCountStates();
        rd.addRows(rdSimulation);
        rd.addRows(rdEnvironment);
        //rd.addRows(rdAgentCountStates);
        if(withInterface){
            MainFrame.getInstance().appendLineToOutput(this.handleDataToString(rd, "essential"));
        }

        essentialData.addRow(rd);
    }

    public void addLineDetailed(Agent a){
        RowData rd = new RowData();
        RowData dataSim = this.simulation.getDataDetailed();
        RowData dataEnv = this.environment.getDataDetailed();
        RowData dataAgent = a.getDataDetailed();

        rd.addRows(dataSim);
        rd.addRows(dataEnv);
        rd.addRows(dataAgent);
        //MainFrame.getInstance().appendLineToOutput(this.handleDataToString(rd, "detailed"));
        detailedData.addRow(rd);
    }


    /* Write Files Functions */

    public void writeCSVFile() {
        //Todo write csv file
        //Todo configurationData to Write CSV
        //Todo detailed data
        if(dataHandlerConfig.isEssentialData()){
            WriteFileData(essentialData, "essential");
        }
        if(dataHandlerConfig.isDetailedData()){
            WriteFileData(detailedData, "detailed");
        }
    }

    private void WriteFileData(MatrixData data, String mode){
        try{
            System.out.println("Starting to write the file --> " +mode+" .");
            FileWriter myWriter = new FileWriter(dataHandlerConfig.getExperimentName()+"_"+mode+".csv");
            myWriter.write(data.getCSVFormat());
            myWriter.close();
            System.out.println("Successfully wrote to the file --> " +mode+" .");
        }catch (Exception exp){
            System.out.println("Exception trying to write CSV files in mode: "+mode);
            exp.printStackTrace();
        }
    }


    /* Getters and Setters*/

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public void setSimulation(Simulation simulation){
        this.simulation = simulation;
    }

    public DataHandlerConfig getDataHandlerConfig() {
        return dataHandlerConfig;
    }

    public void setDataHandlerConfig(DataHandlerConfig dataHandlerConfig) {
        this.dataHandlerConfig = dataHandlerConfig;
    }

    public void setExperimentConfig(ExperimentConfig experimentConfigData) {
        this.experimentConfig = experimentConfigData;
    }

    public void setWithInterface(boolean withInterface){
        this.withInterface = withInterface;
    }

    public boolean isWithInterface(){
        return this.withInterface;
    }

    private String handleDataToString(RowData data, String mode){
        if(mode.equals("essential")){
            return handleStringEssential(data);
        //}else if(mode.equals("detailed")){
        //    return handleStringDetailed(data);
        }else{
            System.out.println("Error in dataHandler to handleDataToString, mode not know");
            return "ERROR-->ERROR--> MODE";
        }
    }

    private String handleStringEssential(RowData data){
        StringBuilder str = new StringBuilder();
        str.append(" Essential: \n");
        for(int i = 0; i<data.getRows().size(); i++){
            String toAppend = " ---- "+data.getHead().get(i)+ " :" + data.getRows().get(i)+"\n";
            str.append(toAppend);
        }
        //str.append(" \n");
        return str.toString();
    }

    private String handleStringDetailed(RowData data){
        StringBuilder str = new StringBuilder();
        str.append(" Detailed: \n");
        for(int i = 0; i<data.getRows().size(); i++){
            String toAppend = " ----> "+data.getHead().get(i)+ " :" + data.getRows().get(i)+"\n";
            str.append(toAppend);
        }
        //str.append(" \n");
        return str.toString();
    }
}
