package model.util.factory;

import model.environments.twitter.TwitterAgent;
import model.essentials.Agent;
import model.util.actions.Action;

import java.util.ArrayList;

public class AgentFactory {
    private int cantAgentsConfigs;

    public AgentFactory(){
        this.cantAgentsConfigs = 0;
    }

    public Agent createAgentSeed(ArrayList<Action> actionsList){
        return new TwitterAgent(-1, Agent.WAITING, actionsList, true, cantAgentsConfigs++);
    }

    public Agent createAgent(ArrayList<Action> actionsList) {
        return new TwitterAgent(-1, Agent.WAITING, actionsList, false, cantAgentsConfigs++);
    }
}
