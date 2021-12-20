package model.util.factory;


import model.util.actions.ActionAgent;
import model.util.actions.actions_agents.ActionAgentRead;
import model.util.actions.actions_agents.ActionAgentShare;

public class ActionFactory {

    public ActionFactory(){

    }

    public ActionAgent createReadAction(String name, double probability){
        return new ActionAgentRead(name, probability);
    }

    public ActionAgent createReadAction(double probability){
        return new ActionAgentRead("read", probability);
    }

    public ActionAgent createShareAction(String name, double probability){
        return  new ActionAgentShare(name, probability);
    }

    public ActionAgent createShareAction(double probability){
        return  new ActionAgentShare("share", probability);
    }


}
