package model.util.actions.actions_agents;

import model.util.actions.ActionAgent;
import model.essentials.Agent;


public class ActionAgentShare extends ActionAgent {

    public ActionAgentShare(String name, double probability ) {
        super(name, probability);
    }
    //Todo fix states to make a fixed shared message.
    @Override
    public void Execute(Agent a) {
        double p1 = getRandom();//Obten la probabilidad de compartir
        if(p1/100 > (1 - probability)) {
            a.setState(Agent.PREPARE_FOR_SHARE);
        }
    }



}
