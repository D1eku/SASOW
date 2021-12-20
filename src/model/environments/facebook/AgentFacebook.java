package model.environments.facebook;

import model.essentials.Agent;
import model.util.actions.ActionAgent;
import model.util.config.AgentConfig;

import java.util.ArrayList;

public class AgentFacebook extends Agent {

    public AgentFacebook(int id, int state, ArrayList<ActionAgent> cmd_config, boolean isSeed, AgentConfig agentConfig) {
        super(id, state, cmd_config, isSeed, agentConfig);
    }

    @Override
    public void doActions() {
        //add stuff what facebook users do
        share();
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
