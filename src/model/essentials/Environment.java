package model.essentials;

import model.environments.twitter.TwitterAgent;
import model.util.config.AgentConfig;
import model.util.data.IDataDetailed;
import model.util.data.IDataEssential;
import model.util.data.RowData;
import model.util.datahandler.DataHandler;
import model.util.datahandler.observer.IObservable;

import java.util.ArrayList;

public abstract class Environment implements IObservable, IDataEssential, IDataDetailed {
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
        this.users_cant = 0;
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
            createAgents(configAgent);
        }

        this.initialized = true;
        this.addFollowers();
        this.addFollowings();
        if(!this.allDone()){
            System.out.println("ERROR AL INICIALIZAR");
            System.exit(1);
        }
        System.out.println("End Initialize in Environment: ");
    }

    private boolean allDone() {
        boolean isDone = true;
        if(users.size() != this.NetworkSize){
            System.out.println("El NetworkSize esta BUG");
            return false;
        }

        if(seeds.size() != this.SeedSize){
            System.out.println("Seed Size esta BUG");
            return false;
        }

        for (Agent u: users) {
            AgentConfig ag = u.getAgentConfig();
            if(u.getFollowers().size() != ag.getCantFollowers()){
                System.out.println("Error en la cantidad de seguidores del usuario: "+u.getId());
                isDone = false;
                break;
            }
        }

        return isDone;
    }

    private void addFollowings() {
        System.out.println("Adding Followings");
        for (Agent user: this.users) {//Por cada agente, Obten N agentes que tengan su id diferente a alguna indexada
            AgentConfig agentConfig = user.getAgentConfig();
            while(user.getFollowings().size() != (agentConfig.getCantFollowings())) {
                int max = this.users.size();
                int randomIndex = (int) (Math.random() * ((max - 1) + 1) + 0);
                user.addFollowing(this.users.get(randomIndex));
            }
        }
        System.out.println("End Adding Followings");
    }

    private void addFollowers(){
        System.out.println("Adding Followers");
        for (Agent user: this.users) {//Por cada agente, Obten N agentes que tengan su id diferente a alguna indexada
            AgentConfig agentConfig = user.getAgentConfig();
            while(user.getFollowers().size() != (agentConfig.getCantFollowers())) {
                int max = this.users.size();
                int randomIndex = (int) (Math.random() * ((max - 1) + 1) + 0);
                user.addFriend(this.users.get(randomIndex));
            }
        }
        System.out.println("End Adding Followers");
    }

    private void createAgents(AgentConfig configAgent){
        System.out.println("Starting Create agents");
        for(int j = 0; j<configAgent.getCantAgent(); j++){
            Agent info = configAgent.getAgentInfo();//Obten la informacion del agenteConfig
            Agent newAgent =  new TwitterAgent(this.users_cant, info.getState(), info.getCommands(), configAgent.getIsSeed(),configAgent);//Crea un nuevo agente
            users.add(newAgent);//Agregalo a la lista de agentes.
            if(newAgent.isSeed()){
                this.seeds.add(newAgent);
            }
            this.users_cant++;
        }
        System.out.println("End Create agents");
    }

    public RowData getDataEssential() {
        RowData rdEnvironment = new RowData();
        rdEnvironment.addRow(period, "simulation_period");
        rdEnvironment.addRows(getCountStates());
        return rdEnvironment;
    }

    @Override
    public RowData getDataDetailed() {
        RowData rd = new RowData();
        rd.addRow(period, "simulation_period");
        return rd;
    }

    public abstract RowData getCountStates();

    @Override
    public void notifyData() {
        this.dataHandler.updateEssential();
    }

    public ArrayList<Agent> getUsers(){
        return this.users;
    }

    public ArrayList<Agent> getSeeds(){
        return this.seeds;
    }

    public abstract void step();

    public abstract void run();


}
