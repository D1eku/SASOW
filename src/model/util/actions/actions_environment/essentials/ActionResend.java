package model.util.actions.actions_environment.essentials;

import model.essentials.Agent;
import model.essentials.Environment;

public class ActionResend extends ActionEnvironment{
    private int maximumResendTime;
    private int timesReseeded;

    public ActionResend(Environment environment, int maximumResendTime) {
        super("Action Resend Environment", environment);
        this.maximumResendTime = maximumResendTime;
        this.timesReseeded = 0;
    }

    @Override
    public void Execute() {
        if(ResendNow()){
            for(Agent a: environment.getSeeds()){
                a.receiveMessage();
            }

            ++timesReseeded;
        }
    }

    private boolean ResendNow(){
        //Reenviaremos si no hemos reenviado el numero maximo de veces.
        //Si es que se da la probabilidad
        //Asumire 50% de probabilidad de reenviar en algun periodo
        return getRandom() > 50 && timesReseeded< maximumResendTime;
    }

    public int getRandom() {
        return (int )(Math.random() * 100 + 1);
    }
}
