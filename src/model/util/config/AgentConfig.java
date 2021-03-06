package model.util.config;

import model.util.actions.Action;
import model.essentials.Agent;

import java.util.ArrayList;

public class AgentConfig {
    private Agent agentInfo;
    private int cantAgent;
    private double percentageFollowers;
    private double percentageFollowings;
    private ArrayList<Action> actions;
    private String name;

    public AgentConfig(Agent agent, int cantAgent, double followers, double followings){
        this.cantAgent = cantAgent;
        this.agentInfo = agent;
        this.percentageFollowers = followers;
        this.percentageFollowings = followings;
        this.actions = agent.getCommands();
        this.name = "agentDefault";
        System.out.println("Followers in agentConfig: "+followers+" --> "+name);
        System.out.println("Followings in agentConfig: "+followings+" --> "+name);
    }

    public AgentConfig(Agent agent, int cantAgent, double followers, double followings, String name ){
        this.cantAgent = cantAgent;
        this.agentInfo = agent;
        this.percentageFollowers = followers;
        this.percentageFollowings = followings;
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

    public double getPercentageFollowers(){
        return this.percentageFollowers;
    }

    public double getPercentageFollowings(){
        return this.percentageFollowings;
    }

    public ArrayList<Action> getActions(){
        return this.actions;
    }

    public void setPercentageFollowers(int followers) {
        this.percentageFollowers = followers;
    }

    public void setPercentageFollowings(int followings) {
        this.percentageFollowings = followings;
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

    public int getQuantityFollowersByNetwork(int network){
        return (int) (this.percentageFollowers *network/100);
    }

    public int getQuantityFollowingsByNetwork(int network){
        return (int) (this.percentageFollowings *network/100);
    }

}
