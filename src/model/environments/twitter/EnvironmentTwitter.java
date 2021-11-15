package model.environments.twitter;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.config.AgentConfig;

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





}
