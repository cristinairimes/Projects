package ro.utcn.cristina.model;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
   private static Thread thread;
    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;


    public Server(Integer N) {
        clients = new LinkedBlockingQueue<>(N);
        waitingPeriod = new AtomicInteger();
        //waitingPeriod.set(0);
    }

    public void addClient(Client nClient) {

        //add task to queue
        //compute task's finish time
        //increment waitingTime
        clients.add(nClient);
        waitingPeriod.incrementAndGet();
    }

    public void run() {


        while (true) {
            try {
                //take next task from queue
                //stop the thread for a time equal with the task's processing period
                //decrement waiting period
                //remove tasks' head
                    Thread.sleep(1000);
                    if(!clients.isEmpty()){
                        Client client=clients.peek();
                        if(client.getTimpProcesare()==1)
                            clients.take();
                        else
                            client.setTimpProcesare(client.getTimpProcesare()-1);

                    }
                this.waitingPeriod.getAndDecrement();

            } catch (InterruptedException exception) {

            }


        }
    }

    public static void writeWaitList(List<Client> generatedClients, FW fileWriter, int time) {
        System.out.println();
        System.out.println("Time: " + time);
        System.out.print("Waiting Clients: ");
        if(!generatedClients.isEmpty()) {
            for (Client i : generatedClients) {
                FW.write("(" + i.getID() + ", " + i.getTimpSosire() + ", " + i.getTimpProcesare() + ")");
                System.out.print("(" + i.getID() + ", " + i.getTimpSosire() + ", " + i.getTimpProcesare() + ")");
            }
            System.out.println();
            FW.write("\n");
        }
    }
    public static Thread getThread() {
        return thread;
    }

    public static void setThread(Thread thread) {
        Server.thread = thread;
    }

    public AtomicInteger getWaitingPeriod() {

        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {

        this.waitingPeriod = waitingPeriod;
    }
    public BlockingQueue<Client> getClients() {

        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {

        this.clients = clients;
    }

    public static void writeFile(Server s, FW fileWriter) {

        for (Client i : s.getClients()) {
            FW.write("(" + i.getID() + ", " + i.getTimpSosire() + ", " + i.getTimpProcesare() + ")");
            System.out.print("(" + i.getID() + ", " + i.getTimpSosire() + ", " + i.getTimpProcesare() + ")");
        }
        System.out.println();
        FW.write("\n");
    }





}
