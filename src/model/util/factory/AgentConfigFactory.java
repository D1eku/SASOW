package model.util.factory;

import model.essentials.Agent;
import model.util.config.AgentConfig;

import java.util.ArrayList;

public class AgentConfigFactory {
    private ArrayList<AgentConfig> agentsConfigs;

    public AgentConfigFactory(){
        agentsConfigs = new ArrayList<>();
    }

    public void addAgentConfig(Agent agentData, int agentCant, int followersCant){
        agentsConfigs.add(new AgentConfig(agentData, agentCant, followersCant));
    }

    public ArrayList<AgentConfig> createAgentConfig() {
        return agentsConfigs;
    }
}
