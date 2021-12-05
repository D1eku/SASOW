package model.util.datahandler.observer;

import model.essentials.Agent;

public interface IObserver {
    //Todo configurationData; --> updateConfiguration ?

    void updateEssential ();
    void updateDetailed (Agent a);
}
