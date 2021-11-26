package model.essentials;

import model.util.actions.Action;
import model.util.data.IData;
import model.util.data.RowData;

import java.util.ArrayList;

public abstract class Agent implements IData {
    public static final int STOP = -1;
    public static final int WAITING = 0;
    public static final int READ = 1;
    public static final int SHARED = 2;

    protected int state;
    protected int agent_id;
    protected ArrayList<Agent> followers;
    protected ArrayList<Action> commands;
    protected boolean isSeed;
    protected int agentConfig;

    public Agent(int id, int state, ArrayList<Action> cmd_config, boolean isSeed, int agentConfig) {
        this.agent_id = id;
        this.state = state;
        this.followers = new ArrayList<>();
        this.commands = cmd_config;
        this.isSeed = isSeed;
        this.agentConfig = agentConfig;
    }

    @Override
    public RowData getData() {
        RowData rd = new RowData();
        rd.addRow(agent_id, "agent_id");
        rd.addRow(state, "agent_state");
        rd.addRow(isSeed, "agent_seed");
        for(int i = 0;i<commands.size(); i++)  {
            Action c = commands.get(i);
            String name = c.getName();
            String prob = ""+c.getProbability();
            rd.addRow(prob, name);
        }
        return rd;
    }

    public abstract void doActions();

    public void share() {
        //Todo Revisar esto ._.
        for(int i = 0; i<this.followers.size() ; i++){
            Agent f = this.followers.get(i);
            f.receiveMessage();
        }
    }

    public void receiveMessage() {
        //Si recibo un mensaje, agrega a la Queue que este agente debe realizar la accion.
        if(state != STOP && state != SHARED) {
            for (Action c : this.commands) {
                c.Execute(this);
            }
        }
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

    /* Getters and Setters */

    public void setState(int state){
        this.state = state;
    }

    public void setId(int id) {
        this.agent_id = id;
    }

    public void setFollowers(ArrayList<Agent> followers) {
        this.followers = followers;
    }

    public void setCommands(ArrayList<Action> commands) {
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

    public ArrayList<Action> getCommands(){
        return this.commands;
    }

    public boolean isSeed() {
        return this.isSeed;
    }

    public int  getAgentConfig() {
        return this.agentConfig;
    }

    public void makeSeed(boolean isSeed){
        this.isSeed = isSeed;
    }

}