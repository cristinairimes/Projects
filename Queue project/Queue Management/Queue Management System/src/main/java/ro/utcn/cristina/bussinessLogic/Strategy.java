package ro.utcn.cristina.bussinessLogic;

import ro.utcn.cristina.model.Server;
import ro.utcn.cristina.model.Client;

import java.util.List;

public interface Strategy {

    void addClient(List<Server> servers, Client client);
}
