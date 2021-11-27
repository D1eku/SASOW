package model.util.config;

import model.util.actions.Action;
import model.essentials.Agent;

import java.util.ArrayList;

public class AgentConfig {
    private Agent agentInfo;
    private int cantAgent;
    private int  cantFollowers;
    private ArrayList<Action> actions;
    private String name;

    public AgentConfig(Agent agent, int cantAgent, int followers){
        this.cantAgent = cantAgent;
        this.agentInfo = agent;
        this.cantFollowers = followers;
        this.actions = agent.getCommands();
        this.name = "agentDefault";
    }

    public AgentConfig(Agent agent, int cantAgent, int followers, String name ){
        this.cantAgent = cantAgent;
        this.agentInfo = agent;
        this.cantFollowers = followers;
        this.actions = agent.getCommands();
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public Boolean getIsSeed() {
        return this.agentInfo.isSeed();
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

    public ArrayList<Action> getActions(){
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

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setIsSeed(boolean isSeed){
        this.getAgentInfo().makeSeed(isSeed);
    }


}
