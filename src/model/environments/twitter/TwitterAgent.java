package model.environments.twitter;

import model.command.Command;
import model.essentials.Agent;

import java.util.ArrayList;

public class TwitterAgent extends Agent {
    public TwitterAgent(int id, int state, ArrayList<Command> cmd_config, boolean isSeed, int agentConfig) {
        super(id, state, cmd_config, isSeed, agentConfig);
    }

    @Override
    public void doActions()  {

        for (Command command : commands) {
            command.Execute(this);
        }

    }

    public Object clone() throws CloneNotSupportedException{
        return (TwitterAgent) super.clone();
    }

}
