package experiments.main.Manuela;

import model.essentials.Agent;
import model.util.actions.actions_agents.essentials.core.ActionAgent;

public class ActionReadManuela extends ActionAgent {
    public ActionReadManuela(String name, double probability) {
        super(name, probability);
    }

    @Override
    public void Execute(Agent agent) {
        double p1 = getRandom();//Calcula la probabilidad de que lo lea
        if(p1/100 > (1 - probability)) {//Si lo lee
            agent.setState(Agent.READ);//Marca al agente como estado leido
            ((AgentManuela) agent).setSaturationLevel(((AgentManuela) agent).getSaturationLevel() + 1);
        }
    }

    //Todo necesitamos reinicializar los estados de los agentes?.
}
