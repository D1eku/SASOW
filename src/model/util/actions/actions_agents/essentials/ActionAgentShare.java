package model.util.actions.actions_agents.essentials;

import model.essentials.Agent;
import model.util.actions.actions_agents.essentials.core.ActionAgent;


public class ActionAgentShare extends ActionAgent {

    public ActionAgentShare(String name, double probability ) {
        super(name, probability);
    }

    @Override
    public void Execute(Agent a) {
        double p1 = getRandom();//Obten la probabilidad de compartir
        if(p1/100 > (1 - probability)) {
            a.setState(Agent.PREPARE_FOR_SHARE);
        }
    }



}
