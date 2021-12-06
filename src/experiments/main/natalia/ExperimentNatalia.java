package experiments.main.natalia;

import model.environments.twitter.SimulationTwitter;
import model.essentials.Experiment;
import model.util.actions.Action;
import model.util.config.AgentConfig;
import model.util.config.DataHandlerConfig;
import model.util.config.SimulationConfig;
import model.util.factory.ActionFactory;
import model.util.factory.AgentConfigFactory;
import model.util.factory.AgentFactory;

import java.util.ArrayList;

public class ExperimentNatalia extends Experiment {
    public ExperimentNatalia(int runs, String name, String description, DataHandlerConfig dataHandlerConfig) {
        super(runs, name, description,dataHandlerConfig);
    }

    @Override
    public void configure() {

        int networkSize = 10105;
        int seedSize = networkSize * 5/100;
        seedSize = 0;
        int periods = 25;

        int followersLeader = 108;
        int followersHub = 142;
        int followersAvr = 6;

        double pRTAvr = 0.193;
        double pRTHub = 0.193;
        double pRTLeader = 0.2509;

        double pRead = 0.2;


        //Se que esto se puede convertir en una funcion
        ActionFactory actionFactory = new ActionFactory();
        AgentFactory agentFactory = new AgentFactory();
        AgentConfigFactory agentConfigFactory = new AgentConfigFactory();

        ArrayList<AgentConfig> ags = new ArrayList<>();


        ArrayList<Action> actList_oscar = new ArrayList<>();
        actList_oscar.add(actionFactory.createReadAction(0.5));
        actList_oscar.add(actionFactory.createShareAction(0.5));
        ags.add(agentConfigFactory.createAgentConfig(agentFactory.createTwitterAgent(actList_oscar), 10000, 50,0));

        ArrayList<Action> actList_casz3 = new ArrayList<>();
        actList_casz3.add(actionFactory.createReadAction(0.5));
        actList_casz3.add(actionFactory.createShareAction(0.3));
        ags.add(agentConfigFactory.createAgentConfig(agentFactory.createTwitterAgentSeed(actList_casz3), 5, 5,0));


        ArrayList<Action> actList_lukk = new ArrayList<>();
        actList_lukk.add(actionFactory.createReadAction(0.5));
        actList_lukk.add(actionFactory.createShareAction(0.2));
        ags.add(agentConfigFactory.createAgentConfig(agentFactory.createTwitterAgent(actList_lukk), 100, 50, 0));


        simulationConfig = new SimulationConfig(periods, networkSize, seedSize, ags);
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
