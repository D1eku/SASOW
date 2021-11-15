package model.environments.twitter;

import model.util.actions.Command;
import model.util.actions.commands.CommandRead;
import model.util.actions.commands.CommandShare;
import model.essentials.Agent;
import model.essentials.Experiment;
import model.util.config.AgentConfig;
import model.util.config.SimulationConfig;

import java.util.ArrayList;

public class ExperimentTwitter extends Experiment {

    public ExperimentTwitter(int repetitions, String name, String description) {
        super(repetitions, name, description);
    }

    @Override
    public void configure() {

        int networkSize = 10000;
        int seedSize = 100;
        int periods = 25;


        //Crea los comandos
        ArrayList<Command> commands = new ArrayList<>();
        CommandShare cmdShare = new CommandShare("SHARE", 0.03);
        CommandRead lRead = new CommandRead("READ_LEADER", 0.5);
        commands.add(cmdShare);
        commands.add(lRead);

        //Crea los tipos de agentes
        Agent avSeedAgent = new TwitterAgent(-1, Agent.STOP, commands, true, 0);
        Agent averageAgent = new TwitterAgent(-1, Agent.WAITING, commands, false, 1);

        //Configura los agentes
        ArrayList<AgentConfig> agentConfigs = new ArrayList<>();

        AgentConfig seedConfig = new AgentConfig(avSeedAgent, seedSize, 100, commands);
        agentConfigs.add(seedConfig);

        AgentConfig averageConfig = new AgentConfig(averageAgent, networkSize - seedSize, 10, commands);
        agentConfigs.add(averageConfig);

        //Configura la simulacion
        simulation_config = new SimulationConfig( periods,networkSize, seedSize, agentConfigs);

    }

}
