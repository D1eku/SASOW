package experiments.main.natalia;

import model.util.config.DataHandlerConfig;

public class Main {



    public static void main(String[] args) {
        int runs = 3;
        String name = "Twitter Natalia";
        String description = "Analisis de cual agente es mejor para compartir mensajes";
        DataHandlerConfig dconfig = new DataHandlerConfig("Casz", true, true);
        ExperimentNatalia exp = new ExperimentNatalia(runs, name, description, dconfig);

        //do config
        System.out.println("Starting Experiment in Main with : "+ runs+ "runs");
        System.out.println("Experiment Name: "+name);
        System.out.println("Experiment Description: "+description);
        //Run
        exp.run();

    }
}
