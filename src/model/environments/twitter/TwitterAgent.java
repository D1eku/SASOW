package model.environments.twitter;

import model.util.actions.Action;
import model.essentials.Agent;
import model.util.data.RowData;

import java.util.ArrayList;

public class TwitterAgent extends Agent {
    public TwitterAgent(int id, int state, ArrayList<Action> cmd_config, boolean isSeed, int agentConfig) {
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
        rd.addRow("Some Data", "name_some_data");
        return rd;
    }
}
