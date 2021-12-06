package model.util.factory;

import model.essentials.Agent;
import model.util.config.AgentConfig;

import java.util.ArrayList;

public class AgentConfigFactory {
    private ArrayList<AgentConfig> agentsConfigs;

    public AgentConfigFactory(){
        agentsConfigs = new ArrayList<>();
    }


    public AgentConfig createAgentConfig(Agent agentData, int agentCant, double followers, double followings){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followers, followings);
        agentsConfigs.add(agentConfig);
        return agentConfig;
    }

    public AgentConfig createAgentConfig(Agent agentData, int agentCant, double followersCant, double followings, String name){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followersCant, followings, name);
        agentsConfigs.add(agentConfig);
        return agentConfig;
    }

}
