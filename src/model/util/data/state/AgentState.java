package model.util.data.state;

import model.essentials.Agent;

public class AgentState {

    //Informacion que quiero saber del agente.
    private Agent agent;

    public AgentState(Agent a) throws CloneNotSupportedException {
        this.agent = (Agent) a.clone();
    }
}
