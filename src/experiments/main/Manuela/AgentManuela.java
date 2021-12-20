package experiments.main.Manuela;

import model.environments.twitter.TwitterAgent;
import model.util.actions.actions_agents.essentials.core.ActionAgent;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class AgentManuela extends TwitterAgent {

    private boolean saturated;
    private int saturationLevel;
    private int saturationThreshold;

    public AgentManuela(int id,int state, ArrayList<ActionAgent> cmd_config, boolean isSeed, AgentConfig agentConfig, int threshHold) {
        super(id, state, cmd_config, isSeed, agentConfig);
        agentTypo = "ManuelaAgent";
        saturated = false;
        saturationLevel = 0;
        saturationThreshold = threshHold;
    }

    @Override
    public RowData getDataDetailed() {
        RowData rd = super.getDataDetailed();
        rd.addRow(saturated, "is_saturated");
        rd.addRow(saturationLevel,"saturated_level");
        rd.addRow(saturationThreshold, "saturationThreshHold");
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

    public void setSaturationLevel(int level){
        System.out.println("Setting saturation level at: "+saturationLevel);
        if(level >= saturationThreshold){
            saturationLevel = saturationThreshold;
            this.setSaturated(true);
        }else{
            saturationLevel = level;
        }
    }

    public int getSaturationLevel(){
        return this.saturationLevel;
    }

    public void setSaturated(boolean saturated) {
        this.saturated = saturated;
    }

    public boolean isSaturated(){
        return this.saturated;
    }
}
