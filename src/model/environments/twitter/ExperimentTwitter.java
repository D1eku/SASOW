package model.environments.twitter;

import model.command.Command;
import model.command.actions.CommandShare;
import model.essentials.Agent;
import model.essentials.Experiment;
import model.util.config.AgentConfig;
import model.util.config.SimulationConfig;

import java.util.ArrayList;

public class ExperimentTwitter extends Experiment {

    public ExperimentTwitter(int repetitions, String name, String description) {
        super(repetitions, name, description);

        int STOP = -1;
        int WAITING = 0;
        int READ = 1;
        int SHARED = 2;

        int networkSize = 10000;
        int seedSize = 100;
        int periods = 25;


        //Crea los comandos
        ArrayList<Command> commands = new ArrayList<>();
        CommandShare cmdShare = new CommandShare("SHARE", 0.03);
        commands.add(cmdShare);

        //Crea los tipos de agentes
        Agent avSeedAgent = new TwitterAgent(-1, SHARED, commands, true, 0);
        Agent averageAgent = new TwitterAgent(-1, WAITING, commands, false, 1);

        //Configura los agentes
        ArrayList<AgentConfig> agentConfigs = new ArrayList<>();

        AgentConfig seedConfig = new AgentConfig(avSeedAgent, seedSize, 100, commands);
        agentConfigs.add(seedConfig);

        AgentConfig averageConfig = new AgentConfig(averageAgent, networkSize - seedSize, 10, commands);
        agentConfigs.add(averageConfig);

        //Configura la simulacion
        SimulationConfig sc = new SimulationConfig( periods,networkSize, seedSize, agentConfigs);
        this.simulation_config  = sc;

        //Crea la simulacion
        simulation = new SimulationTwitter(sc);
    }


}
