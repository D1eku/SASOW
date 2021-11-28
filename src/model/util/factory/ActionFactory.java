package model.util.factory;


import model.util.actions.Action;
import model.util.actions.commands.ActionRead;
import model.util.actions.commands.ActionShare;

public class ActionFactory {

    public ActionFactory(){

    }

    public Action createReadAction(String name, double probability){
        return new ActionRead(name, probability);
    }

    public Action createReadAction( double probability){
        return new ActionRead("read", probability);
    }

    public Action createShareAction(String name, double probability){
        return  new ActionShare(name, probability);
    }

    public Action createShareAction( double probability){
        return  new ActionShare("share", probability);
    }


}
