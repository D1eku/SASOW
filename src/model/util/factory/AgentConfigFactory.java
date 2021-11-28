package model.util.factory;

import model.essentials.Agent;
import model.util.config.AgentConfig;

import java.util.ArrayList;

public class AgentConfigFactory {
    private ArrayList<AgentConfig> agentsConfigs;

    public AgentConfigFactory(ArrayList<AgentConfig> prevConfig){
        agentsConfigs = prevConfig;
    }

    public AgentConfigFactory(){
        agentsConfigs = new ArrayList<>();
    }


    public AgentConfig createAgentConfig(Agent agentData, int agentCant, int followersCant){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followersCant);
        agentsConfigs.add(agentConfig);
        return agentConfig;
    }

    public AgentConfig createAgentConfig(Agent agentData, int agentCant, int followersCant, String name){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followersCant, name);
        agentsConfigs.add(agentConfig);
        return agentConfig;
    }

    public AgentConfig addAgentConfig(Agent agentData, int agentCant, int followersCant){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followersCant);
        agentsConfigs.add(agentConfig);
        return agentConfig;
    }

    public AgentConfig addAgentConfig(Agent agentData, int agentCant, int followersCant, String name){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followersCant, name);
        agentsConfigs.add(agentConfig);
        return agentConfig;
    }

    public ArrayList<AgentConfig> createAgentConfig() {
        return agentsConfigs;
    }
}
