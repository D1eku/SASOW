package model.util.config;

import java.util.ArrayList;

public class SimulationConfig {
    private int networkSize;
    private ArrayList<AgentConfig> agentsConfigs;
    private int periods;


    public SimulationConfig(int periods, int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentConfigs) {
        this.networkSize = NetworkSize;
        this.agentsConfigs = agentConfigs;
        this.periods = periods;
    }


    public int getNetworkSize() {
        return this.networkSize;
    }


    public ArrayList<AgentConfig> getAgentsConfigs(){
        return this.agentsConfigs;
    }

    public int getPeriods(){
        return this.periods;
    }

    public int getSeedSize(){
        int total = 0;
        for(int i = 0; i<agentsConfigs.size(); i++) {
            if(agentsConfigs.get(i).getIsSeed()){
                total+= agentsConfigs.get(i).getCantAgent();
            }
        }
        return total;
    }


}
