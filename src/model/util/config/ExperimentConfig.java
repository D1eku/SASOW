package model.util.config;

public class ExperimentConfig {
    private String name;
    private String description;
    private int repetitions;

    private boolean essentialData;
    private boolean configuratorData;
    private boolean detailedData;
    private SimulationConfig simulationConfig;

    public ExperimentConfig(SimulationConfig simulationConfig){
        this.simulationConfig = simulationConfig;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isConfiguratorData() {
        return configuratorData;
    }

    public boolean isDetailedData() {
        return detailedData;
    }

    public boolean isEssentialData() {
        return essentialData;
    }

    public void setConfiguratorData(boolean configuratorData) {
        this.configuratorData = configuratorData;
    }

    public void setDetailedData(boolean detailedData) {
        this.detailedData = detailedData;
    }

    public void setEssentialData(boolean essentialData) {
        this.essentialData = essentialData;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }
}
