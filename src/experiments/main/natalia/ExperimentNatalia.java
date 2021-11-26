package experiments.main.natalia;

import model.essentials.Experiment;
import model.util.actions.Action;
import model.util.config.SimulationConfig;
import model.util.factory.ActionFactory;
import model.util.factory.AgentConfigFactory;
import model.util.factory.AgentFactory;

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
        ActionFactory actionFactory = new ActionFactory();
        AgentFactory agentFactory = new AgentFactory();
        AgentConfigFactory agentConfigFactory = new AgentConfigFactory();

        //Agente Average
        ArrayList<Action> actionsAverage = new ArrayList<>();
        actionsAverage.add(actionFactory.createReadAction(pRead));
        actionsAverage.add(actionFactory.createShareAction(pRTAvr));

        //Agente HUB
        ArrayList<Action> actionsHub = new ArrayList<>();
        actionsHub.add(actionFactory.createReadAction(pRead));
        actionsHub.add(actionFactory.createShareAction(pRTHub));


        //Agente Leader
        ArrayList<Action> actionsLeader = new ArrayList<>();
        actionsLeader.add(actionFactory.createReadAction(pRead));
        actionsLeader.add(actionFactory.createShareAction(pRTLeader));


        //Configuracion de agentes
        agentConfigFactory.addAgentConfig(agentFactory.createAgentSeed(actionsLeader), seedSize, followersHub);
        agentConfigFactory.addAgentConfig(agentFactory.createAgent(actionsAverage), networkSize - seedSize, followersAvr);

        simulation_config = new SimulationConfig(periods, networkSize, seedSize, agentConfigFactory.createAgentConfig());
    }





}
