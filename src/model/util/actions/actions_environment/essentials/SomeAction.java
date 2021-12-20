package model.util.actions.actions_environment.essentials;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.actions.actions_environment.essentials.core.ActionEnvironment;

public class SomeAction extends ActionEnvironment {
    //TODO GIVE A REALISTIC NAME  to te class PLS ._.

    public SomeAction(Environment environment) {
        super("ACCION QUE SE EJECUTA CUANDO period ES DISTINTO DE 0", environment);
    }

    @Override
    public void Execute() {
        if(environment.getPeriod() != 0 ){
            for (Agent agent: environment.getUsers() ) {
                agent.doActions();
            }
        }
    }




}
