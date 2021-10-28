package model.util.data;

import model.essentials.Experiment;

public class ExperimentState {
    private Experiment experiment;

    public ExperimentState(Experiment experiment) throws CloneNotSupportedException {
        experiment = (Experiment) experiment.clone();
    }

    public void printData(){
        System.out.println(experiment.toString());
    }

}
