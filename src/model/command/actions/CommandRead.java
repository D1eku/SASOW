package model.command.actions;

import model.command.Command;
import model.essentials.Agent;

public class CommandRead extends Command {

    public CommandRead(String name, double probability ) {
        super(name, probability);
    }

    @Override
    public void Execute(Agent a) {
        if (a.getState() == Agent.WAITING){//Si esta esperando, entonces puede leer
            double p1 = getRandom();//Calcula la probabilidad de que lo lea
            if(p1/100 > (1 - probability)) {//Si lo lee
                a.setState(Agent.READ);//Marca al agente como estado leido
            }
        }
    }
}
