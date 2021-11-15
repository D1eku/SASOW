package model.main.natalia;

import model.util.actions.Command;
import model.util.actions.commands.CommandRead;
import model.util.actions.commands.CommandShare;
import model.environments.twitter.TwitterAgent;
import model.essentials.Agent;
import model.essentials.Experiment;
import model.util.config.AgentConfig;
import model.util.config.SimulationConfig;

import java.util.ArrayList;

public class ExperimentNatalia extends Experiment {
    public ExperimentNatalia(int runs, String name, String description) {
        super(runs, name, description);
    }

    @Override
    public void configure() {

        int networkSize = 10000;
        int seedSize = networkSize * 5/100;
        int periods = 25;

        int followersLeader = 108;
        int followersHub = 142;
        int followersAvr = 6;

        double pRTAvr = 0.193;
        double pRTHub = 0.193;
        double pRTLeader = 0.2509;

        double pRead = 0.2;

        //Todo Agent Factory
        //Todo Command Factory
        //Todo Make function to configure ?

        //Se que esto se puede convertir en una funcion

        //Agente Average
        ArrayList<Command> actionsAverage = new ArrayList<>();
        CommandRead avrRead = new CommandRead("READ_AVR", pRead);
        CommandShare avrShare = new CommandShare("SHARE_AVR", pRTAvr);
        actionsAverage.add(avrRead);
        actionsAverage.add(avrShare);
        TwitterAgent avrAgent = new TwitterAgent(-1, Agent.WAITING, actionsAverage, false, 1);


        //Agente HUB
        ArrayList<Command> actionsHub = new ArrayList<>();
        CommandRead hubRead = new CommandRead("READ_HUB", pRead);
        CommandShare hubShare = new CommandShare("SHARE_HUB", pRTHub);
        actionsHub.add(hubRead);
        actionsHub.add(hubShare);
        TwitterAgent HUBAgent = new TwitterAgent(-1, Agent.WAITING, actionsAverage, true, 0);


        //Agente Leader
        ArrayList<Command> actionsLeader = new ArrayList<>();
        CommandRead lRead = new CommandRead("READ_LEADER", pRead);
        CommandShare lShare = new CommandShare("SHARE_LEADER", pRTLeader);
        actionsLeader.add(lRead);
        actionsLeader.add(lShare);
        TwitterAgent leadAgent = new TwitterAgent(-1, Agent.WAITING, actionsAverage, true, 0);

        //Configuracion de agentes
        ArrayList<AgentConfig> agentsConfigs = new ArrayList<>();
        AgentConfig seedConfig = new AgentConfig(HUBAgent, seedSize, followersHub, actionsHub );
        agentsConfigs.add(seedConfig);

        AgentConfig avrConfig = new AgentConfig(avrAgent, networkSize - seedSize, followersAvr, actionsAverage);
        agentsConfigs.add(avrConfig);

        simulation_config = new SimulationConfig(periods, networkSize, seedSize, agentsConfigs);
    }





}
