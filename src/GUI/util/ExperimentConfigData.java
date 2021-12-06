package GUI.util;

import java.util.ArrayList;
import java.util.Properties;

public class ExperimentConfigData {
    private ArrayList<AgentConfiguratorData> agentConfData;
    private String name;
    private String description;
    private int repetitions;
    private int networkSize;
    private int seedSize;
    private int periods;
    private boolean essentialData;
    private boolean detailedData;


    public ExperimentConfigData(){
        this.agentConfData = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public int getNetworkSize() {
        return networkSize;
    }

    public int getPeriods() {
        return periods;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public int getSeedSize() {
        return seedSize;
    }

    public void setAgentConfData(ArrayList<AgentConfiguratorData> agentConfData) {
        this.agentConfData = agentConfData;
    }

    public ArrayList<AgentConfiguratorData> getAgentConfData() {
        return agentConfData;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNetworkSize(int networkSize) {
        this.networkSize = networkSize;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public void setSeedSize(int seedSize) {
        this.seedSize = seedSize;
    }

    public boolean isDetailedData() {
        return detailedData;
    }

    public boolean isEssentialData() {
        return essentialData;
    }

    public void setDetailedData(boolean detailedData) {
        this.detailedData = detailedData;
    }

    public void setEssentialData(boolean essentialData) {
        this.essentialData = essentialData;
    }

    public Properties toCFGFile(){
        Properties prop = new Properties();
        prop.setProperty("experimentName", this.name);
        prop.setProperty("description",this.description);
        prop.setProperty("repetitions",""+this.repetitions);
        prop.setProperty("networkSize",""+this.networkSize);
        prop.setProperty("seedSize",""+this.seedSize);
        prop.setProperty("periods", ""+this.periods);
        prop.setProperty("essentialData",""+this.essentialData);
        prop.setProperty("detailedData",""+this.detailedData);
        prop.setProperty("agentConfigSize",this.agentConfData.size()+"");
        for(int i = 0; i<this.agentConfData.size(); i++){
            AgentConfiguratorData a = this.agentConfData.get(i);
            prop.setProperty("name_"+i,a.getAgentConfigName());
            prop.setProperty("agentTypo_"+i, a.getAgentTypo());
            prop.setProperty("followers_"+i,""+ a.getFollowersByNetwork(networkSize));
            prop.setProperty("followings_"+i, ""+a.getFollowingsByNetwork(networkSize));
            prop.setProperty("seed_"+i, ""+a.isSeed());
            prop.setProperty("quantityAgent_"+i,""+ a.getQuantityAgent());
            prop.setProperty("actionsSizeAgent_"+i,a.getActionsData().size()+"");
            for(int j = 0; j<a.getActionsData().size(); j++){
                ActionData ad = a.getActionsData().get(j);
                prop.setProperty("nameAction_"+i+"_"+j, ad.getName());
                prop.setProperty("probabilityAction_"+i+"_"+j,""+ad.getProbability());
                prop.setProperty("typeAction_"+i+"_"+j,ad.getType());
            }

        }
        return prop;
    }

    public void loadFromCFG(Properties prop){
        this.name = prop.getProperty("experimentName");
        this.description = prop.getProperty("description");
        this.repetitions = Integer.parseInt(prop.getProperty("repetitions")) ;
        this.networkSize = Integer.parseInt(prop.getProperty("networkSize"));
        this.seedSize = Integer.parseInt(prop.getProperty("seedSize"));
        this.periods = Integer.parseInt(prop.getProperty("periods"));
        this.essentialData = Boolean.parseBoolean(prop.getProperty("essentialData"));
        this.detailedData = Boolean.parseBoolean(prop.getProperty("detailedData"));
        int agentConfigSize = Integer.parseInt(prop.getProperty("agentConfigSize"));
        for(int i = 0; i<agentConfigSize; i++){
            AgentConfiguratorData a = new AgentConfiguratorData();
            a.setAgentConfigName(prop.getProperty("name_"+i));
            a.setAgentTypo(prop.getProperty("agentTypo_"+i));
            //int cantFollowers = quantityByNetwork(networkSize, Double.parseDouble(prop.getProperty("followers_"+i)));
            double cantFollowers = Double.parseDouble(prop.getProperty("followers_"+i));
            System.out.println("Cantidad de seguidores en loadFromCfg: "+cantFollowers);
            a.setFollowers(cantFollowers);
            double cantFollowings = Double.parseDouble(prop.getProperty("followings_"+i));
            a.setFollowings(cantFollowings);
            a.setSeed(Boolean.parseBoolean(prop.getProperty("seed_"+i)));
            a.setQuantityAgent(Integer.parseInt(prop.getProperty("quantityAgent_"+i)));
            int actionsAgentSize = Integer.parseInt(prop.getProperty("actionsSizeAgent_"+i));
            ArrayList<ActionData> list = a.getActionsData();
            agentConfData.add(a);
            for(int j = 0; j<actionsAgentSize; j++){
                String nameAction = prop.getProperty("nameAction_"+i+"_"+j);
                String typeAction = prop.getProperty("typeAction_"+i+"_"+j);
                double probAction = Double.parseDouble(prop.getProperty("probabilityAction_"+i+"_"+j));
                System.out.println("DATA DE LA ACTION ("+j+")");
                System.out.println("nameAction: "+nameAction);
                System.out.println("typeAction: "+typeAction);
                System.out.println("probAction: "+probAction);
                ActionData ad = new ActionData(nameAction,probAction,typeAction);
                list.add(ad);
            }
        }
    }

}
