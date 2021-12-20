package model.essentials;

import model.util.actions.actions_agents.essentials.core.ActionAgent;
import model.util.config.AgentConfig;
import model.util.data.IDataDetailed;
import model.util.data.RowData;
import model.util.datahandler.observer.IObservable;

import java.util.ArrayList;

public abstract class Agent implements IDataDetailed, IObservable {
    public static final int SHARED = -1;
    public static final int NOREAD = 0;
    public static final int READ = 1;
    public static final int PREPARE_FOR_SHARE = 2;

    protected int state;
    protected int agent_id;
    protected ArrayList<Agent> followers;
    protected ArrayList<Agent> followings;
    protected ArrayList<ActionAgent> commands;
    protected Boolean isSeed;
    protected AgentConfig agentConfig;

    public Agent(int id, int state, ArrayList<ActionAgent> cmd_config, boolean isSeed, AgentConfig agentConfig) {
        this.agent_id = id;
        this.state = state;
        this.followers = new ArrayList<>();
        this.followings = new ArrayList<>();
        this.commands = cmd_config;//todo this can make better
        this.isSeed = isSeed;
        this.agentConfig = agentConfig;
    }

    public RowData getDataDetailed() {
        RowData rd = new RowData();
        rd.addRow(agent_id, "agent_id");
        rd.addRow(state, "agent_state");
        rd.addRow(isSeed, "agent_seed");
        rd.addRow(agentConfig.getName(),"agent_type");
        return rd;
    }

    public abstract void doActions();

    public void receiveMessage() {
        if(this.state == NOREAD){
            getActionName("read").Execute(this);
            if(this.state == READ) {
                getActionName("share").Execute(this);
            }
        }
    }

    protected void sendMessage(){
        for(int i = 0; i<getFollowers().size() ; i++){
            Agent f = getFollowers().get(i);
            f.receiveMessage();
        }
    }

    protected ActionAgent getActionName(String name){
        for(ActionAgent c : this.commands) {
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    @Override
    public void notifyData() {
        //DataHandler.getInstance().updateDetailed(this);
    }

    public void addFriend(Agent agent) {
        boolean exist = false;
        if(agent.getId() == this.agent_id) {
            exist = true;
        }else{
            for(int i = 0; i<this.followers.size(); i++){
                if(this.followers.get(i).getId() == agent.getId()) {
                    exist = true;
                    break;
                }
            }
        }

        if(!exist) {
            this.followers.add(agent);
        }//El agente esta repetido

    }

    public void addFollowing(Agent agent){
        boolean exist = false;
        if(agent.getId() == this.agent_id) {
            exist = true;
        }else{
            for(int i = 0; i<this.followers.size(); i++){
                if(this.followers.get(i).getId() == agent.getId()) {
                    exist = true;
                    break;
                }
            }
        }

        if(!exist) {
            this.followings.add(agent);
        }//El agente esta repetido
    }

    /* Getters and Setters */

    public void setState(int state){
        this.state = state;
        notifyData();
    }

    public void setId(int id) {
        this.agent_id = id;
    }

    public void setFollowers(ArrayList<Agent> followers) {
        this.followers = followers;
    }

    public void setCommands(ArrayList<ActionAgent> commands) {
        this.commands = commands;
    }

    public int getState() {
        return this.state;
    }

    public int getId(){
        return this.agent_id;
    }

    public ArrayList<Agent> getFollowers(){
        return this.followers;
    }

    public ArrayList<Agent> getFollowings(){
        return this.followings;
    }

    public void setFollowings(ArrayList<Agent> followings){
        this.followings = followings;
    }

    public ArrayList<ActionAgent> getCommands(){
        return this.commands;
    }

    public boolean isSeed() {
        return this.isSeed;
    }

    public AgentConfig getAgentConfig() {
        return agentConfig;
    }

    public void makeSeed(boolean isSeed){
        this.isSeed = isSeed;
    }

}