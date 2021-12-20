package model.util.actions.actions_environment.essentials;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.actions.actions_environment.essentials.core.ActionEnvironment;

public class ActionStart extends ActionEnvironment {

    public ActionStart(Environment environment) {
        super("action_start", environment);
    }

    @Override
    public void Execute() {
        if( environment.getPeriod() == 0){
            for(Agent seed: environment.getSeeds()){
                seed.receiveMessage();
            }
        }
    }
}
