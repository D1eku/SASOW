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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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


    //public void updateEssential() {
    //    if(dataHandlerConfig.isEssentialData()){
    //        addLineEssential();
    //    }
    //}

    //public void updateDetailed() {
    //    if(dataHandlerConfig.isDetailedData()){
    //        addLineDetailed();
    //    }
    //}

    @Override
    public void update() {
        if(dataHandlerConfig.isEssentialData()){
            addLineEssential();
        }
        if(dataHandlerConfig.isDetailedData()){
            addLineDetailed();
        }
    }

    /*
    Este metodo se deberia llamar cada vez que se setea el period en environment
     */
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

    public void addLineDetailed(){
        RowData dataSim = this.simulation.getDataDetailed();
        RowData dataEnv = this.environment.getDataDetailed();
        ArrayList<Agent> users = this.environment.getUsers();

        for(Agent user: users){
            RowData rd = new RowData();
            rd.addRows(dataSim);
            rd.addRows(dataEnv);
            rd.addRows(user.getDataDetailed());
            detailedData.addRow(rd);
        }
    }


    /* Write Files Functions */

    public void writeCSVFile() {
        //Todo write csv file
        //Todo configurationData to Write CSV
        //Todo detailed data
        if(dataHandlerConfig.isEssentialData()){
            WriteFileData(essentialData, "essential");
            essentialData = new MatrixData();
        }
        if(dataHandlerConfig.isDetailedData()){
            WriteFileData(detailedData, "detailed");
            detailedData = new MatrixData();
        }
    }

    private void WriteFileData(MatrixData data, String mode){
        try{
            System.out.println("Starting to write the file --> " +mode+" .");
            DateFormat df = new SimpleDateFormat("dd-MM-yy(HH-mm-ss)");
            String date = "_" + df.format(new Date());
            FileWriter myWriter = new FileWriter("./"+dataHandlerConfig.getExperimentName()+date+"_"+mode+".csv");
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
        if(!mode.equals("essential") || !mode.equals("detailed")){
            return handleStringEssential(data);
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
