package model.notimplemented.message;

import model.essentials.Agent;

public class Reaction {
    private Emotion emotion;
    private Agent author;

    public Reaction(Agent author, Emotion emotion){
        this.author = author;
        this.emotion = emotion;
    }
}
