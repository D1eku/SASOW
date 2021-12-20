package experiments.main.Manuela;

import model.environments.twitter.SimulationTwitter;
import model.util.actions.actions_environment.essentials.ActionStart;
import model.util.actions.actions_environment.essentials.SomeAction;
import model.util.config.SimulationConfig;
import model.util.data.RowData;

public class SimulationManuela extends SimulationTwitter {

    private int saturationThreshHold;
    private int maxResends;

    public SimulationManuela(int id, SimulationConfig simulationConfig, int saturationThreshHold, int maxResends) {
        super(id, simulationConfig);
        this.saturationThreshHold = saturationThreshHold;
        this.maxResends = maxResends;
        this.environment = new EnvironmentManuela(id,periods, NetworkSize, SeedSize, simulationConfig.getAgentsConfigs());
        //Todo delete below this, this is only for manuela doActions implementations
        /*
        En t0, un conjunto de agentes (seguidores) recibe el mensaje que se difunde a través de la red.
         En cualquier período de una simulación, una marca podría reenviar el mensaje (por ejemplo, t4),
         aumentando la posibilidad de aumentar la alcance sino también de agentes saturantes.
         */
        environment.getActions().add(new ActionStart(environment));
        environment.getActions().add(new SomeAction(environment));
        environment.getActions().add(new ActionResend(environment,maxResends));
    }

    @Override
    public RowData getDataEssential(){
        RowData rd = new RowData();
        rd.addRow(maxResends,"max_resends");
        rd.addRow(saturationThreshHold, "saturation_threshold");
        RowData sRd = super.getDataEssential();
        sRd.addRows(rd);
        return sRd;
    }

    public int getSaturationThreshHold(){
        return saturationThreshHold;
    }

    public int getMaxResends(){
        return maxResends;
    }
}
