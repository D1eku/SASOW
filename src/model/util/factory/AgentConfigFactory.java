package model.util.factory;

import model.essentials.Agent;
import model.util.config.AgentConfig;

public class AgentConfigFactory {

    public AgentConfig createAgentConfig(Agent agentData, int agentCant, double followers, double followings){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followers, followings);
        return agentConfig;
    }

    public AgentConfig createAgentConfig(Agent agentData, int agentCant, double followersCant, double followings, String name){
        AgentConfig agentConfig = new AgentConfig(agentData, agentCant, followersCant, followings, name);
        return agentConfig;
    }

}
