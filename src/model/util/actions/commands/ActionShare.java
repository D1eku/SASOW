package model.util.actions.commands;

import model.util.actions.Action;
import model.essentials.Agent;


public class ActionShare extends Action {

    public ActionShare(String name, double probability ) {
        super(name, probability);
    }
    //Todo fix states to make a fixed shared message.
    @Override
    public void Execute(Agent g) {
        if(g.getState() == Agent.READ){//Si leyo entonces puede compartir
            double p1 = getRandom();//Obten la probabilidad de compartir
            if(p1/100 > (1 - probability)) {
                g.setState(Agent.SHARED);
                g.share();
            }
        }
    }



}
