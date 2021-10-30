package model.environments.twitter;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public class EnvironmentTwitter extends Environment {

    public EnvironmentTwitter(int id, int periods,int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentsConfig)  {
        super(id, periods,NetworkSize, SeedSize, agentsConfig);
    }

    @Override
    public void step() {
        System.out.println("Do Step ( "+ period + ") of "+periods);
        for (Agent agent: users ) {
            agent.doActions();
        }
    }

    @Override
    public void run() {
        System.out.println("Starting in Environment ");
        setPeriod(0);
        while(period < periods) {
            step();
            setPeriod(++period);
        }
    }


    public RowData countStates() {
        RowData rd = new RowData();
        int cantStop = 0;
        int cantWaiting = 0;
        int cantRead = 0;
        int cantShared = 0;
        for (Agent user: users) {
            switch (user.getState()) {
                case Agent.STOP -> cantStop++;
                case Agent.WAITING -> cantWaiting++;
                case Agent.READ -> cantRead++;
                case Agent.SHARED -> cantShared++;
            }
        }

        //System.out.println("Count States: ");
        //System.out.println("Cant Stop: "+cantStop);
        //System.out.println("Cant Wait: "+cantWaiting);
        //System.out.println("Cant Read: "+cantRead);
        //System.out.println("Cant Shared: "+cantShared);
        rd.addRow(cantStop);
        rd.addRow(cantWaiting);
        rd.addRow(cantRead);
        rd.addRow(cantShared);
        return rd ;
    }


}
