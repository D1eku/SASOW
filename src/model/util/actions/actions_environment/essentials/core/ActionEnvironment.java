package model.util.actions.actions_environment.essentials.core;

import model.essentials.Environment;

public abstract class ActionEnvironment {
    protected String name;
    protected Environment environment;

    public ActionEnvironment(String name, Environment environment){
        this.name = name;
        this.environment = environment;
    }

    public abstract void Execute();


    public void setName(String name ){
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
