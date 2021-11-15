package model.util.config;

import model.util.actions.Command;
import model.essentials.Agent;

import java.util.ArrayList;

public class AgentConfig {
    private Agent agentInfo;
    private int cantAgent;
    private int  cantFollowers;
    private ArrayList<Command> actions;

    public AgentConfig(Agent agent, int cantAgent, int followers, ArrayList<Command> actions){
        this.cantAgent = cantAgent;
        this.agentInfo = agent;
        this.cantFollowers = followers;
        this.actions = actions;
    }

    public Agent getAgentInfo() {
        return this.agentInfo;
    }

    public int getCantAgent(){
        return this.cantAgent;
    }

    public int getCantFollowers(){
        return this.cantFollowers;
    }

    public ArrayList<Command> getActions(){
        return this.actions;
    }

    public void setCantFollowers(int cantFollowers) {
        this.cantFollowers = cantFollowers;
    }

    public void setAgentInfo(Agent agentClass){
        this.agentInfo = agentClass;
    }

    public void setCantAgent(int cantAgent) {
        this.cantAgent = cantAgent;
    }

    public void setActions(ArrayList<Command> actions) {
        this.actions = actions;
    }
}
