package model.environments.twitter;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.actions.actions_environment.essentials.ActionEnvironment;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class EnvironmentTwitter extends Environment {

    public EnvironmentTwitter(int id, int periods,int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentsConfig)  {
        super(id, periods,NetworkSize, SeedSize, agentsConfig);

        //Todo delete below this, this is only for natalia doActions implementations
        /*
        En t0, un conjunto de agentes (seguidores) recibe el mensaje que se difunde a través de la red.
         En cualquier período de una simulación, una marca podría reenviar el mensaje (por ejemplo, t4),
         aumentando la posibilidad de aumentar la alcance sino también de agentes saturantes.
         */


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

    @Override
    public void doActions(){
        for(ActionEnvironment  action: actions){
            action.Execute();
        }
    }

    @Override
    public void step() {
        System.out.println("Do Step ("+ (period+1) + ") of "+(periods));
        doActions();
    }

    @Override
    public void run() {
        System.out.println("Starting in Environment ");
        int p = 0;
        setPeriod(p);
        while(period < (periods-1)) {
            step();
            ++p;
            setPeriod(p);
        }
    }





}
