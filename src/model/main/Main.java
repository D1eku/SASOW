package model.main;

import model.environments.twitter.ExperimentTwitter;

public class Main {

    public static void main(String[] args) {
        int runs = 1;
        String name = "Twitter Manuela Run";
        String description = "Experiment to test influence and saturation of a one message";
        ExperimentTwitter exp = new ExperimentTwitter(runs, name, description);
        //do config
        System.out.println("Starting Experiment in Main with : "+ runs+ "runs");
        System.out.println("Experiment Name: "+name);
        System.out.println("Experiment Description: "+description);
        exp.run();
    }
}
