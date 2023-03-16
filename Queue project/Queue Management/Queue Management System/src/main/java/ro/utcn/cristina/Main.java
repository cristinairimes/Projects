package ro.utcn.cristina;


import ro.utcn.cristina.bussinessLogic.SimulationManager;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SimulationManager sm = new SimulationManager();
        Thread t = new Thread(sm);
        t.start();
        t.stop();
    }
}
