package model.util.factory;

import experiments.main.Manuela.AgentManuela;
import model.environments.facebook.AgentFacebook;
import model.environments.twitter.TwitterAgent;
import model.essentials.Agent;
import model.util.actions.actions_agents.essentials.core.ActionAgent;

import java.util.ArrayList;

public class AgentFactory {

    public Agent createTwitterAgentSeed(ArrayList<ActionAgent> actionsList){
        return new TwitterAgent(-1, Agent.NOREAD, actionsList, true, null);
    }

    public Agent createTwitterAgent(ArrayList<ActionAgent> actionsList) {
        return new TwitterAgent(-1, Agent.NOREAD, actionsList, false, null);
    }

    public Agent createFacebookAgent(ArrayList<ActionAgent> actionsList) {
        return new AgentFacebook(-1, Agent.NOREAD, actionsList, false, null);
    }

    public Agent createFacebookAgentSeed(ArrayList<ActionAgent> actionsList){
        return new AgentFacebook(-1, Agent.NOREAD, actionsList, true, null);
    }

    public Agent createManuelaAgent(ArrayList<ActionAgent> actionsList, int threshHold) {
        return new AgentManuela(-1, Agent.NOREAD, actionsList, false, null, threshHold);
    }

    public Agent createManuelaSeedAgent(ArrayList<ActionAgent> actionsList, int threshHold) {
        return new AgentManuela(-1, Agent.NOREAD, actionsList, true, null, threshHold);
    }
}
