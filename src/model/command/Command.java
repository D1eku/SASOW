package model.command;

import model.essentials.Agent;

public abstract class Command {

    protected String name;
    protected double probability;

    public Command(String name, double probability) {
        this.name = name;
        this.probability = probability;
    }

    public abstract void Execute(Agent agent);

    public String getName(){
        return this.name;
    }

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
