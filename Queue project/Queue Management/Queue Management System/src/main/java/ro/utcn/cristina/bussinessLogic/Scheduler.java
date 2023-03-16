package ro.utcn.cristina.bussinessLogic;

import ro.utcn.cristina.model.Server;
import ro.utcn.cristina.model.Client;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private List<Server> servers;
    private Strategy strategy;
    private int maxClientsPerServer;
    private int maxNoServers;

    public Scheduler(int maxNoServers, int maxClientsPerServer){
        this.setMaxNoServers(maxNoServers);
        this.setMaxClientsPerServer(maxClientsPerServer);
        this.servers = new ArrayList<>();
        for (int i = 0; i < maxNoServers; i++) {
            Server s = new Server(maxClientsPerServer);
            Thread t = new Thread(s);
            servers.add(s);
            s.setThread(t);
            s.getThread().start();
        }



    }

    public void changeStrategy(StrategyPolicy policy){
        /*switch(policy) {
            case SHORTEST_TIME:
                //stategy=
                break;
            case SHORTEST_QUEUE:
                //strategy=
                break;
        }*/
        if(policy==StrategyPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == StrategyPolicy.SHORTEST_TIME){
            strategy= new ConcreteStrategyTime();
        }
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public int getMaxNoServers() {
        return maxNoServers;
    }

    public void setMaxNoServers(int maxNoServers) {
        this.maxNoServers = maxNoServers;
    }

    public void dispatchTask(Client client){
        //call the strategy addTask method
        strategy.addClient(servers, client);
    }
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
    public List<Server> getServers(){
        return servers;
    }

    public int getMaxClientsPerServer() {
        return maxClientsPerServer;
    }

    public void setMaxClientsPerServer(int maxClientsPerServer) {
        this.maxClientsPerServer = maxClientsPerServer;
    }
}
