package ro.utcn.cristina.bussinessLogic;

import ro.utcn.cristina.gui.View;
import ro.utcn.cristina.model.FW;
import ro.utcn.cristina.model.Server;
import ro.utcn.cristina.model.Client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.*;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;



public class SimulationManager implements Runnable {
    public int timeLimit;
    public int numberOfServers;
    public int numberOfClients ;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int minArrivalTime ;
    public int maxArrivalTime ;

    private Scheduler scheduler;
    private View frame;
    private List<Client> generatedClients;
    public StrategyPolicy selectionPolicy = StrategyPolicy.SHORTEST_TIME;
    private String file;
    private FW filewr;

    public SimulationManager() {
        frame = new View();
        frame.setVisible(true);
        //file = "Result.txt";
        //file = "Result2.txt";
        //file = "Result3.txt";
        file = "Result4.txt";
        filewr= new FW(file);


        frame.getStart().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    numberOfClients  = Integer.parseInt(frame.getClients().getText());
                    numberOfServers = Integer.parseInt(frame.getQueues().getText());
                    timeLimit = Integer.parseInt(frame.getIntervalS().getText());
                    maxProcessingTime = Integer.parseInt(frame.getMaxiService().getText());
                    minProcessingTime = Integer.parseInt(frame.getMiniService().getText());
                    maxArrivalTime = Integer.parseInt(frame.getMaxiArrival().getText());
                    minArrivalTime = Integer.parseInt(frame.getMiniArrival().getText());
                    scheduler = new Scheduler(numberOfServers, numberOfClients );
                    scheduler.changeStrategy(selectionPolicy);
                    generateRandomClients();
                    run();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "You entered the wrong data");
                }
            }
        });
    }


    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public ArrayList<Client> getGeneratedTasks() {
        return (ArrayList<Client>) generatedClients;
    }

    public void setGeneratedTasks(ArrayList<Client> generatedClients) {
        this.generatedClients = generatedClients;
    }


    public void generateRandomClients() {
        //new Task(random(min,max), random(min, max));
        generatedClients = new ArrayList<Client>();
        for (int i = 0; i < numberOfClients ; i++) {
            int randProcTime = ThreadLocalRandom.current().nextInt(minProcessingTime, maxProcessingTime + 1);
            int randArrvTime = ThreadLocalRandom.current().nextInt(minArrivalTime, maxArrivalTime + 1);
            generatedClients.add(new Client(i, randArrvTime, randProcTime));
        }
        Collections.sort(generatedClients);

    }

    int nrS = 0;
    @Override
    public void run() {
        int currentTime = 0;
        while (currentTime < timeLimit) {
            try {
                FW.write("\nTime: "+currentTime+"\nWaiting Clients: ");
               Server.writeWaitList(generatedClients, filewr, currentTime);
                for (Client i : generatedClients) {
                    if (i.getTimpSosire() == currentTime) {
                        scheduler.dispatchTask(new Client(i.getID(), i.getTimpSosire(), i.getTimpProcesare()));
                        i.setID(-1);;
                    }
                }
               FW.write("\n");
                System.out.println();

                scheduler.getServers().forEach(i->{
                    nrS++;
                    System.out.print("Queue "+nrS+": ");
                    FW.write("Queue "+nrS+": ");
                    frame.setTextArea("Queue "+nrS+": ");
                    if(!i.getClients().isEmpty())
                       Server.writeFile(i, filewr);
                    else {
                        System.out.print("closed\n");
                        FW.write("closed\n");
                    }
                    System.out.println();
                });
                nrS=0;
                Iterator<Client> i = generatedClients.iterator();
                while (i.hasNext()) {
                    Client t = i.next();
                    if (t.getID() == -1)
                        i.remove();
                }
                currentTime++;
                Thread.sleep(1000);

            } catch (InterruptedException e) {
            }
        }

        if(filewr!=null)
            filewr.close();

    }


}







