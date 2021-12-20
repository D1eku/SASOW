package experiments.main.Manuela;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.actions.actions_environment.essentials.core.ActionEnvironment;

public class ActionReloadStates extends ActionEnvironment {

    public ActionReloadStates(Environment environment) {
        super("reload_states", environment);
    }

    @Override
    public void Execute() {
        for(Agent agent: environment.getUsers()){
            agent.setState(Agent.NOREAD);
        }
    }
}
