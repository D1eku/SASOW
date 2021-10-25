package model.environments.twitter;

import model.command.Command;

import java.util.ArrayList;

public class testUser extends TwitterAgent{
    public testUser(int id, int state, ArrayList<Command> cmd_config, boolean isSeed, int agentConfig) {
        super(id, state, cmd_config, isSeed, agentConfig);
    }
}
