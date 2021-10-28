package model.command.actions;

import model.command.Command;
import model.essentials.Agent;


public class CommandShare extends Command {

    public CommandShare(String name, double probability ) {
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
