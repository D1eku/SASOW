package model.environments.twitter;

import model.util.actions.Action;
import model.essentials.Agent;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class TwitterAgent extends Agent {
    public TwitterAgent(int id, int state, ArrayList<Action> cmd_config, boolean isSeed, AgentConfig agentConfig) {
        super(id, state, cmd_config, isSeed, agentConfig);
    }

    @Override
    public void doActions()  {

        for (Action command : commands) {
            command.Execute(this);
        }

    }

    @Override
    public RowData getData() {
        //ADD some info to add to csv file.
        RowData rd = super.getData();
        return rd;
    }
}
