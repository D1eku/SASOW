package GUI.util;


public class ActionData {

    private String name;
    private double probability;
    private String type;

    public ActionData(String name, Double probability, String type) {
        this.name = name;
        this.probability = probability;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    public String getName() {
        return this.name;
    }

    public double getProbability() {
        return this.probability;
    }

    public String getType() {
        return this.type;
    }
}
