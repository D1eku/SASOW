package model.environments.twitter;

import model.util.actions.actions_agents.essentials.core.ActionAgent;
import model.util.actions.actions_agents.essentials.ActionAgentRead;
import model.util.actions.actions_agents.essentials.ActionAgentShare;
import model.essentials.Agent;
import model.essentials.Experiment;
import model.util.config.AgentConfig;
import model.util.config.DataHandlerConfig;
import model.util.config.SimulationConfig;

import java.util.ArrayList;

public class ExperimentTwitter extends Experiment {

    public ExperimentTwitter(int repetitions, String name, String description, DataHandlerConfig dataHandlerConfig) {
        super(repetitions, name, description, dataHandlerConfig);
    }

    @Override
    public void configure() {

        int networkSize = 10000;
        int seedSize = 100;
        int periods = 25;


        //Crea los comandos
        ArrayList<ActionAgent> commands = new ArrayList<>();
        ActionAgentShare cmdShare = new ActionAgentShare("SHARE", 0.03);
        ActionAgentRead lRead = new ActionAgentRead("READ_LEADER", 0.5);
        commands.add(cmdShare);
        commands.add(lRead);

        //Crea los tipos de agentes
        Agent avSeedAgent = new TwitterAgent(-1, Agent.PREPARE_FOR_SHARE, commands, true, null);
        Agent averageAgent = new TwitterAgent(-1, Agent.NOREAD, commands, false, null);

        //Configura los agentes
        ArrayList<AgentConfig> agentConfigs = new ArrayList<>();

        AgentConfig seedConfig = new AgentConfig(avSeedAgent, seedSize, 100, 0);
        agentConfigs.add(seedConfig);

        AgentConfig averageConfig = new AgentConfig(averageAgent, networkSize - seedSize, 10, 0);
        agentConfigs.add(averageConfig);

        //Configura la simulacion
        simulationConfig = new SimulationConfig( periods,networkSize, seedSize, agentConfigs);

    }

    @Override
    public void initialize(int id) {
        simulation = new SimulationTwitter(id, simulationConfig);
        simulation.setExperiment(this);
        simulation.initialize();
        this.dataHandler.setExperiment(this);
        this.dataHandler.setSimulation(simulation);
        this.dataHandler.setEnvironment(simulation.getEnvironment());
    }

}
