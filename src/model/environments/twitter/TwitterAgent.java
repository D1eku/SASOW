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
        share();
    }

    @Override
    public RowData getDataDetailed() {
        //ADD some info to add to csv file.
        RowData rd = super.getDataDetailed();
        return rd;
    }

    private void share(){
        if(state == PREPARE_FOR_SHARE){
            for(int i = 0; i<getFollowers().size() ; i++){
                Agent f = getFollowers().get(i);
                f.receiveMessage();
            }
            setState(Agent.SHARED);
        }
    }
}
