package model.environments.twitter;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.config.AgentConfig;
import model.util.data.EnvironmentInfo;
import model.util.data.StepInfo;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EnvironmentTwitter extends Environment {

    public EnvironmentTwitter(int id, int periods,int NetworkSize, int SeedSize, ArrayList<AgentConfig> agentsConfig)  {
        super(id, periods,NetworkSize, SeedSize, agentsConfig);
    }

    @Override
    public StepInfo step() {
        System.out.println("Do Step ( "+ period + ") of "+periods);
        StepInfo stepInfo = new StepInfo(period, users);
        for (Agent agent: users ) {
            agent.doActions();
        }
        stepInfos.add(stepInfo);
        return stepInfo;
    }

    @Override
    public EnvironmentInfo run() {
        System.out.println("Starting in Environment ");
        ArrayList<int[]> states = new ArrayList<>();
        for(period = 0; period<periods; period++){
            states.add(countStates());
            //Todo make clone all data of agents.

            step();
        }
        //Todo clone after do last step.
        states.add(countStates());
        return new EnvironmentInfo(this.stepInfos);
    }

    @Contract(" -> new")
    private int @NotNull [] countStates() {
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

        System.out.println("Count States: ");
        System.out.println("Cant Stop: "+cantStop);
        System.out.println("Cant Wait: "+cantWaiting);
        System.out.println("Cant Read: "+cantRead);
        System.out.println("Cant Shared: "+cantShared);

        return new int[]{cantStop, cantWaiting, cantRead, cantShared};
    }

    private void extraAnalysis(@NotNull ArrayList<int[]> states){
        System.out.println("========================================================");
        System.out.println("STARTING EXTRA ANALYSIS");
        for(int i = 0; i<(states.size()-1); i++){

            System.out.println("I - (I -1 ) = " + i);

            int stop =  states.get(i+1)[0] -states.get(i)[0];
            int wait =   states.get(i)[1] - states.get(i+1)[1];
            int read =  states.get(i+1)[2] - states.get(i)[2];
            int shared =   states.get(i+1)[3] - states.get(i)[3];

            System.out.println("Count States: ");
            System.out.println("Cant Stop: "+stop);
            System.out.println("Cant Wait: "+wait);
            System.out.println("Cant Read: "+read);
            System.out.println("Cant Shared: "+shared);

        }
        System.out.println("========================================================");
    }

}
