package model.util.data;

import model.essentials.Agent;

import java.util.ArrayList;

public class StepInfo {

    private int iteration;
    private ArrayList<Agent> users;
    private ArrayList<AgentInfo> agentInfos;

    public StepInfo(int iteration, ArrayList<Agent> users){
        this.iteration = iteration;
        this.users = users;
        this.agentInfos = new ArrayList<>();
    }

    public int getIteration() {
        return  this.iteration;
    }

    public ArrayList<Agent> getUsers(){
        return this.users;
    }

    public ArrayList<AgentInfo> getAgentInfos(){
        return this.agentInfos;
    }

    public void GenerateData(){
        //Work with users states in the period == iteration

    }


}
