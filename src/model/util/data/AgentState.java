package model.util.data;

import model.essentials.Agent;

public class AgentState {

    //Informacion que quiero saber del agente.
    private Agent agent;

    public AgentState(Agent a) throws CloneNotSupportedException {
        this.agent = (Agent) a.clone();
    }
}
