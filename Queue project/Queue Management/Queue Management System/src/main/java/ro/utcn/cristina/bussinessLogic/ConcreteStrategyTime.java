package ro.utcn.cristina.bussinessLogic;

import ro.utcn.cristina.model.Server;
import ro.utcn.cristina.model.Client;

import java.util.List;

public class ConcreteStrategyTime implements Strategy{
    @Override
    public void addClient(List<Server> servers, Client client) {
        int poz = 0;
        int min = servers.get(0).getWaitingPeriod().intValue();
        for (int i = 0; i < servers.size(); i++) {
            int aux = servers.get(i).getWaitingPeriod().intValue();
            if (aux < min) {
                min = aux;
                poz = i;
            }
        }
        servers.get(poz).addClient(client);
    }

}


