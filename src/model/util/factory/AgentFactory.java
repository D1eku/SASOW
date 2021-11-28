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

    public Agent createTwitterAgentSeed(ArrayList<Action> actionsList){
        return new TwitterAgent(-1, Agent.WAITING, actionsList, true, null);
    }

    public Agent createTwitterAgent(ArrayList<Action> actionsList) {
        return new TwitterAgent(-1, Agent.WAITING, actionsList, false, null);
    }
}
