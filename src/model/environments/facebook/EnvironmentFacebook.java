package model.environments.facebook;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class EnvironmentFacebook extends Environment {
    public EnvironmentFacebook(int id, int periods, int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentsConfigs) {
        super(id, periods, NetworkSize, SeedSize, agentsConfigs);
    }

    @Override
    public void step() {
        //Add stuff what facebookEnvironment can do
        System.out.println("Do Step ("+ (period+1) + ") of "+(periods));
        if(period == 0){
            for(Agent seed: seeds){
                seed.receiveMessage();
            }
        }else {
            for (Agent agent: users ) {
                agent.doActions();
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Starting in Environment Facebook ");
        setPeriod(0);
        while(period < periods-1) {
            step();
            setPeriod(++period);
        }
    }

    @Override
    public RowData getCountStates() {
        RowData rd = new RowData();
        int prepared = 0;
        int noread = 0;
        int read = 0;
        int shared = 0;
        for (Agent user: users) {
            switch (user.getState()) {
                case Agent.PREPARE_FOR_SHARE:
                    prepared++;
                    break;
                case Agent.NOREAD:
                    noread++;
                    break;
                case Agent.READ:
                    read++;
                    break;
                case Agent.SHARED:
                    shared++;
                    break;
            }
        }
        rd.addRow(noread, "state_noread");//Son los que no hicieron nada
        rd.addRow(read, "state_count_read");
        rd.addRow(prepared, "state_count_prepared");//A la proxima iteracion comparten
        rd.addRow(shared, "state_count_shared");
        return rd ;
    }
}
