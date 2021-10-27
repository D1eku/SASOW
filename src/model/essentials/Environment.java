package model.essentials;

import model.environments.twitter.TwitterAgent;
import model.util.data.StepInfo;
import model.util.config.AgentConfig;
import model.util.data.EnvironmentInfo;

import java.util.ArrayList;

public abstract class Environment {
    protected int NetworkSize;
    protected int SeedSize;
    protected int periods;
    protected int period;

    protected boolean initialized;
    protected ArrayList<Agent> users;
    protected int users_cant;
    protected ArrayList<Agent> seeds;
    protected ArrayList<AgentConfig> agentsConfigs;

    protected ArrayList<StepInfo> stepInfos;

    public Environment(int periods, int NetworkSize,int SeedSize, ArrayList<AgentConfig> agentsConfigs){
        this.agentsConfigs = agentsConfigs;
        this.NetworkSize = NetworkSize;
        this.SeedSize = SeedSize;
        this.users = new ArrayList<>();
        this.seeds = new ArrayList<>();
        this.initialized = false;
        this.stepInfos = new ArrayList<>();
        this.users_cant = -1;
        this.periods =periods;
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
        this.period = 0;
    }

    private boolean allDone() {
        boolean isDone = true;
        if(users.size() != this.NetworkSize){
            isDone = false;
            return false;
        }

        if(seeds.size() != this.SeedSize){
            isDone= false;
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

    public abstract StepInfo step();

    public abstract EnvironmentInfo run();
}
