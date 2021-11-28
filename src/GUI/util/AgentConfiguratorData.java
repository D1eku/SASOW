package GUI.util;

import java.util.ArrayList;

public class AgentConfiguratorData {

    private String agentConfigName;
    private int followers;
    private int followings;
    private String agentTypo;
    private ArrayList<ActionData> actionsData;
    private boolean seed;
    private int quantityAgent;

    public AgentConfiguratorData(String name, String agentTypo, int followers, int followings, ArrayList<ActionData> actionsData){
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

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public void setFollowings(int followings) {
        this.followings = followings;
    }

    public void setAgentTypo(String typo) {
        this.agentTypo = typo;
    }

    public String getAgentConfigName() {
        return this.agentConfigName;
    }

    public int getFollowers() {
        return this.followers;
    }

    public int getFollowings() {
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


}
