package model.util.config;

import java.util.ArrayList;

public class SimulationConfig {
    private int NetworkSize;
    private AgentConfig seedConfig;
    private ArrayList<AgentConfig> agentsConfigs;
    private int periods;


    public SimulationConfig(int periods, int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentConfigs) {
        this.NetworkSize = NetworkSize;
        //this.SeedSize = SeedSize;
        this.agentsConfigs = agentConfigs;
        this.seedConfig = this.agentsConfigs.get(0);
        //this.SeedAgentClass = SeedAgentClass;
        this.periods = periods;
    }

    public AgentConfig getSeedConfig(){
        return this.seedConfig;
    }

    public int getNetworkSize() {
        return this.NetworkSize;
    }

    public int getSeedSize() {
        return this.seedConfig.getCantAgent();
    }

    public ArrayList<AgentConfig> getAgentsConfigs(){
        return this.agentsConfigs;
    }

    public int getPeriods(){
        return this.periods;
    }


}
