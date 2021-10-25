package model.natalia;

public class Main {

    public static void main(String[] args) {
        int runs = 1;
        String name = "Twitter Natalia";
        String description = "Analisis de cual agente es mejor para compartir mensajes";
        ExperimentNatalia exp = new ExperimentNatalia(runs, name, description);
        //do config
        System.out.println("Starting Experiment in Main with : "+ runs+ "runs");
        System.out.println("Experiment Name: "+name);
        System.out.println("Experiment Description: "+description);
        exp.run();
    }
}
