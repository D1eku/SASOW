package experiments.main.Manuela;

import model.environments.twitter.EnvironmentTwitter;
import model.essentials.Agent;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class EnvironmentManuela extends EnvironmentTwitter {
    private int resendTime;

    public EnvironmentManuela(int id, int periods, int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentsConfig) {
        super(id, periods, NetworkSize, SeedSize, agentsConfig);
    }

    public RowData getDataEssential(){
        RowData rd = super.getDataEssential();
        rd.addRow(resendTime,"time_resend");
        return rd;
    }

    @Override
    public RowData getCountStates() {
        //Estamos haciendo un uso innecesario de 2 llamadas a recorrer la lista de usuarios pero bueno
        RowData rd = new RowData();
        int saturated_agents = 0;
        for (Agent user: users) {
            if(((AgentManuela)user).isSaturated()){
                saturated_agents++;
            }
        }
        rd.addRow(saturated_agents,"saturated_agents");
        RowData srd = super.getCountStates();
        srd.addRows(rd);
        return srd;
    }

    public int getResendTime(){
        return resendTime;
    }

    public void setResendTime(int resendTime){
        this.resendTime = resendTime;
    }
}
