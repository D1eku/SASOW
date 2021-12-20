package model.util.actions.actions_agents.essentials;

import model.essentials.Agent;
import model.util.actions.actions_agents.essentials.core.ActionAgent;

public class ActionAgentRead extends ActionAgent {

    public ActionAgentRead(String name, double probability ) {
        super(name, probability);
    }

    @Override
    public void Execute(Agent a) {
        double p1 = getRandom();//Calcula la probabilidad de que lo lea
        if(p1/100 > (1 - probability)) {//Si lo lee
            a.setState(Agent.READ);//Marca al agente como estado leido
        }
    }
}
