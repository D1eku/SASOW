package GUI.util;

import java.util.ArrayList;

public class ExperimentDataConfig {
    private ArrayList<AgentConfiguratorData> agentConfData;
    private String name;
    private String description;
    private int repetitions;
    private int networkSize;
    private int seedSize;
    private int periods;


    public ExperimentDataConfig(){
        this.agentConfData = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getNetworkSize() {
        return networkSize;
    }

    public int getPeriods() {
        return periods;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getSeedSize() {
        return seedSize;
    }

    public void setAgentConfData(ArrayList<AgentConfiguratorData> agentConfData) {
        this.agentConfData = agentConfData;
    }

    public ArrayList<AgentConfiguratorData> getAgentConfData() {
        return agentConfData;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetworkSize(int networkSize) {
        this.networkSize = networkSize;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSeedSize(int seedSize) {
        this.seedSize = seedSize;
    }

}
