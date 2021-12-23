package model.util.actions.actions_agents.essentials.core;

import model.essentials.Agent;

public abstract class ActionAgent {

    protected String name;
    protected double probability;

    public ActionAgent(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }

    public String getName(){
        return this.name;
    }

    public abstract void Execute(Agent agent);

    public double getProbability(){
        return this.probability;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setProbability(Double p){
        this.probability = p;
    }

    public int getRandom() {
        return (int )(Math.random() * 100 + 1);
    }
}
