package model.util.data.state;

import model.essentials.Environment;

public class EnvironmentState {
    private Environment environment;

    public EnvironmentState(Environment env) throws CloneNotSupportedException {
        this.environment = env;
    }
}
