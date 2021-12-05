package GUI.util;

import java.util.ArrayList;

public class AgentConfiguratorData {

    private String agentConfigName;
    private double followers;
    private double followings;
    private String agentTypo;
    private ArrayList<ActionData> actionsData;
    private boolean seed;
    private int quantityAgent;

    public AgentConfiguratorData(String name, String agentTypo, double followers, double followings, ArrayList<ActionData> actionsData){
        this.agentConfigName = name;
        this.agentTypo = agentTypo;
        this.followings = followings;
        this.followers = followers;
        this.actionsData = actionsData;
    }

    public AgentConfiguratorData(){
        actionsData = new ArrayList<>();
    }

    public void setAgentConfigName(String name) {
        this.agentConfigName = name;
    }

    public void setFollowers(double followers) {
        this.followers = followers;
    }

    public void setFollowings(double followings) {
        this.followings = followings;
    }

    public void setAgentTypo(String typo) {
        this.agentTypo = typo;
    }

    public String getAgentConfigName() {
        return this.agentConfigName;
    }

    public double getFollowers() {
        return this.followers;
    }

    public double getFollowings() {
        return this.followings;
    }

    public String getAgentTypo() {
        return agentTypo;
    }

    public ArrayList<ActionData> getActionsData() {
        return this.actionsData;
    }

    public void setActionsData(ArrayList<ActionData> actionsData){
        this.actionsData = actionsData;
    }

    public boolean isSeed() {
        return this.seed;
    }

    public int getQuantityAgent() {
        return this.quantityAgent;
    }

    public void setSeed(boolean seed) {
        this.seed = seed;
    }

    public void setQuantityAgent(int quantityAgent){
        this.quantityAgent = quantityAgent;
    }

    public int getFollowersByNetwork(int network){
        return (int) (network*this.followers);
    }

    public int getFollowingsByNetwork(int network){
        return (int) (network*followings);
    }

}
