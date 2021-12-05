package model.util.config;

public class DataHandlerConfig {
    private boolean essentialData;
    private boolean detailedData;
    private String experimentName;

    public DataHandlerConfig(String experimentName,boolean essentialData, boolean detailedData){
        this.essentialData = essentialData;
        this.detailedData = detailedData;
        this.experimentName = experimentName;
    }

    public DataHandlerConfig(){
        this.essentialData = false;
        this.detailedData = false;
        this.experimentName = "experiment_default";
    }

    public DataHandlerConfig(String name){
        this.essentialData = false;
        this.detailedData = false;
        this.experimentName = name;
    }

    public void setEssentialData(boolean essentialData) {
        this.essentialData = essentialData;
    }

    public void setDetailedData(boolean detailedData) {
        this.detailedData = detailedData;
    }

    public boolean isEssentialData() {
        return essentialData;
    }

    public boolean isDetailedData() {
        return detailedData;
    }

    public String getExperimentName() {
        return experimentName;
    }

    public void setExperimentName(String experimentName) {
        this.experimentName = experimentName;
    }
}
