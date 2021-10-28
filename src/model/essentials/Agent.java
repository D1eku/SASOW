package model.essentials;

import model.command.Command;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class Agent implements Cloneable {
    public static final int STOP = -1;
    public static final int WAITING = 0;
    public static final int READ = 1;
    public static final int SHARED = 2;

    protected int state;
    protected int id;
    protected ArrayList<Agent> followers;
    protected ArrayList<Command> commands;
    protected boolean isSeed;
    protected int agentConfig;

    public Agent(int id, int state, ArrayList<Command> cmd_config, boolean isSeed, int agentConfig){
        this.id = id;
        this.state = state;
        this.followers = new ArrayList<>();
        this.commands = cmd_config;
        this.isSeed = isSeed;
        this.agentConfig = agentConfig;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
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
            for (Command c : this.commands) {
                c.Execute(this);
            }
        }
    }

    public void addFriend(@NotNull Agent agent) {
        boolean exist = false;
        if(agent.getId() == this.id) {
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
        this.id = id;
    }

    public void setFollowers(ArrayList<Agent> followers) {
        this.followers = followers;
    }

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    public int getState() {
        return this.state;
    }

    public int getId(){
        return this.id;
    }

    public ArrayList<Agent> getFollowers(){
        return this.followers;
    }

    public ArrayList<Command> getCommands(){
        return this.commands;
    }

    public boolean isSeed() {
        return this.isSeed;
    }

    public int  getAgentConfig() {
        return this.agentConfig;
    }

}