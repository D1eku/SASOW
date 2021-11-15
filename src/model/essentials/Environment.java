package model.essentials;

import model.environments.twitter.TwitterAgent;
import model.util.datahandler.DataHandler;
import model.util.data.IData;
import model.util.datahandler.observer.IObservable;
import model.util.config.AgentConfig;
import model.util.data.RowData;

import java.util.ArrayList;

public abstract class Environment implements IObservable, IData {
    protected DataHandler dataHandler;
    protected int environment_id;
    protected int NetworkSize;
    protected int SeedSize;
    protected int periods;
    protected int period;
    protected Simulation simulation;

    protected boolean initialized;
    protected ArrayList<Agent> users;
    protected int users_cant;
    protected ArrayList<Agent> seeds;
    protected ArrayList<AgentConfig> agentsConfigs;


    public Environment(int id, int periods, int NetworkSize,int SeedSize, ArrayList<AgentConfig> agentsConfigs){
        this.environment_id = id;
        this.agentsConfigs = agentsConfigs;
        this.NetworkSize = NetworkSize;
        this.SeedSize = SeedSize;
        this.users = new ArrayList<>();
        this.seeds = new ArrayList<>();
        this.initialized = false;
        this.users_cant = -1;
        this.periods =periods;
        this.dataHandler = DataHandler.getInstance();
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

    public void setPeriod(int p){
        this.period = p;
        this.notifyData();
    }

    public void initialize() {
        System.out.println("Initializing in Environment: ");
        for(int i = 0; i<agentsConfigs.size(); i++){
            AgentConfig configAgent = agentsConfigs.get(i);//Obtener i esima configuracion de tipo de agente
            createAgents(configAgent, i==0);
        }

        this.initialized = true;
        this.addFollowers();
        if(!this.allDone()){
            System.out.println("Alerta");
        }
        System.out.println("End Initialize in Environment: ");
    }

    private boolean allDone() {
        boolean isDone = true;
        if(users.size() != this.NetworkSize){
            return false;
        }

        if(seeds.size() != this.SeedSize){
            return false;
        }

        for (Agent u: users) {
            AgentConfig ag = this.agentsConfigs.get(u.getAgentConfig());
            if(u.getFollowers().size() != ag.getCantFollowers()){
                isDone = false;
                break;
            }
        }

        return isDone;
    }

    private void addFollowers(){
        System.out.println("Adding Followers");
        for (Agent user: this.users) {//Por cada agente, Obten N agentes que tengan su id diferente a alguna indexada
            int agentConfig = user.getAgentConfig();
            while(user.getFollowers().size() != (agentsConfigs.get(agentConfig).getCantFollowers())) {
                int max = this.users.size();
                int randomIndex = (int) (Math.random() * ((max - 1) + 1) + 0);
                user.addFriend(this.users.get(randomIndex));
            }
        }
        System.out.println("End Adding Followers");
    }

    private void createAgents(AgentConfig configAgent, boolean isSeed){
        System.out.println("Starting Create agents");
        for(int j = 0; j<configAgent.getCantAgent(); j++){
            Agent info = configAgent.getAgentInfo();//Obten la informacion del agenteConfig

            Agent newAgent =  new TwitterAgent(this.users_cant, info.getState(), info.getCommands(), isSeed, this.agentsConfigs.indexOf(configAgent));//Crea un nuevo agente
            users.add(newAgent);//Agregalo a la lista de agentes.
            if(newAgent.isSeed()){
                newAgent.setState(Agent.STOP);
                this.seeds.add(newAgent);
            }
            this.users_cant++;
        }
        System.out.println("End Create agents");
    }

    public RowData getData() {
        RowData rdEnvironment = new RowData();
        rdEnvironment.addRow(period, "simulation_period");
        return rdEnvironment;
    }

    public RowData getCountStates() {
        RowData rd = new RowData();
        int cantStop = 0;
        int cantWaiting = 0;
        int cantRead = 0;
        int cantShared = 0;
        for (Agent user: users) {
            switch (user.getState()) {
                case Agent.STOP -> cantStop++;
                case Agent.WAITING -> cantWaiting++;
                case Agent.READ -> cantRead++;
                case Agent.SHARED -> cantShared++;
            }
        }
        rd.addRow(cantStop, "state_count_stop");
        rd.addRow(cantWaiting, "state_count_waiting");
        rd.addRow(cantRead, "state_count_read");
        rd.addRow(cantShared, "state_count_shared");
        return rd ;
    }

    @Override
    public void notifyData() {
        this.dataHandler.update();
    }

    public abstract void step();

    public abstract void run();


}
