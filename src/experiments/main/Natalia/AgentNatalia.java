package experiments.main.Natalia;

import model.environments.twitter.TwitterAgent;
import model.util.actions.ActionAgent;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class AgentNatalia extends TwitterAgent {

    private boolean saturated;
    private int saturated_level;

    public AgentNatalia(int id, int state, ArrayList<ActionAgent> cmd_config, boolean isSeed, AgentConfig agentConfig) {
        super(id, state, cmd_config, isSeed, agentConfig);
        saturated = false;
        saturated_level = 0;
    }

    public RowData getDataDetailed() {
        RowData rd = super.getDataDetailed();
        rd.addRow(saturated, "is_saturated");
        rd.addRow(saturated_level,"saturated_level");
        return rd;
    }

    @Override
    public void doActions(){
        special_share();
    }

    private void special_share(){
        if(state == PREPARE_FOR_SHARE){
            sendMessage();
            setState(SHARED);
        }
    }




    public void setSaturated(boolean saturated) {
        this.saturated = saturated;
    }

    public boolean isSaturated(){
        return this.saturated;
    }
}
