package model.message;

import model.essentials.Agent;
import model.message.Emotion;

public class Reaction {
    private Emotion emotion;
    private Agent author;

    public Reaction(Agent author, Emotion emotion){
        this.author = author;
        this.emotion = emotion;
    }
}
