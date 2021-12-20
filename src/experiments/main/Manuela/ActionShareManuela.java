package experiments.main.Manuela;

import model.essentials.Agent;
import model.util.actions.actions_agents.essentials.core.ActionAgent;

public class ActionShareManuela extends ActionAgent {

    public ActionShareManuela(String name, double probability ) {
        super(name, probability);
    }

    @Override
    public void Execute(Agent agent) {
        double p1 = getRandom();//Obten la probabilidad de compartir
        if(p1/100 > (1 - probability) && !((AgentManuela) agent).isSaturated() ) {
            agent.setState(Agent.PREPARE_FOR_SHARE);
        }
    }
}
