package model.util.data.state;

import model.essentials.Experiment;

public class ExperimentState {
    private Experiment experiment;

    public ExperimentState(Experiment experiment) throws CloneNotSupportedException {
        this.experiment = (Experiment) experiment.clone();
    }

    public void printData(){

        System.out.println(experiment.toString());
    }

}
