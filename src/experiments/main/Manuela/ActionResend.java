package experiments.main.Manuela;

import model.essentials.Agent;
import model.essentials.Environment;
import model.util.actions.actions_environment.essentials.core.ActionEnvironment;

public class ActionResend extends ActionEnvironment {
    private int maximumResendTime;
    private int timesResend;

    public ActionResend(Environment environment, int maximumResendTime) {
        super("Action Resend Environment", environment);
        this.maximumResendTime = maximumResendTime;
        this.timesResend = 0;
    }

    @Override
    public void Execute() {
        if(canResendNow()){
            System.out.println("Resending for all USERS, timesResend: "+timesResend);
            System.out.println("Reloading states ");
            for(Agent a: environment.getUsers()){
                a.setState(Agent.NOREAD);
            }
            for(Agent a: environment.getSeeds()){
                a.receiveMessage();
            }
            ++timesResend;
            ((EnvironmentManuela)this.environment).setResendTime(timesResend);
        }
    }

    private boolean canResendNow(){
        //Reenviaremos si no hemos reenviado el numero maximo de veces.
        //Si es que se da la probabilidad
        //Asumire 50% de probabilidad de reenviar en algun periodo
        //Ademas el periodo debe ser mayor a 0
        double randomnumber = getRandom();
        System.out.println("Randomnumber: "+randomnumber);
        return randomnumber > 50 && timesResend < maximumResendTime && environment.getPeriod() > 0;
    }

    public int getRandom() {
        return (int )(Math.random() * 100 + 1);
    }
}
