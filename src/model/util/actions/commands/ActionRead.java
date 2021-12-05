package model.util.actions.commands;

import model.util.actions.Action;
import model.essentials.Agent;

public class ActionRead extends Action {

    public ActionRead(String name, double probability ) {
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
