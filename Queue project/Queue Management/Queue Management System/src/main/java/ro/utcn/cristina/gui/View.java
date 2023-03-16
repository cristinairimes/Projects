package ro.utcn.cristina.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class View extends JFrame{
    private JPanel contentPane;
    private JTextField clienti;
    private JTextField cozi;
    private JTextField intervale;
    private JTextField minArrv;
    private JTextField maxArrv;
    private JTextField minServ;
    private JTextField maxServ;
    private JButton start;
    private JTextArea textArea;

    public JButton getStart() {
        return start;
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    View frame = new View();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public View() {
        setForeground(new Color(255, 153, 102));
        setTitle("Queues Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(204, 153, 153));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel noClients = new JLabel("Clients");
        noClients.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        noClients.setBounds(30, 20, 120, 30);
        contentPane.add(noClients);

        JLabel noQueues = new JLabel("Queues");
        noQueues.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        noQueues.setBounds(238, 20, 120, 30);
        contentPane.add(noQueues);

        JLabel simulation = new JLabel("tMax Simulation");
        simulation.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        simulation.setBounds(30, 83, 120, 30);
        contentPane.add(simulation);

        JLabel miniATime = new JLabel("tMin arrival ");
        miniATime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        miniATime.setBounds(238, 83, 200, 30);
        contentPane.add(miniATime);

        JLabel maxiATime = new JLabel("tMax arrival ");
        maxiATime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxiATime.setBounds(30, 152, 200, 30);
        contentPane.add(maxiATime);

        JLabel miniSTime = new JLabel("tMin service ");
        miniSTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        miniSTime.setBounds(238, 152, 200, 30);
        contentPane.add(miniSTime);

        JLabel maxiSTime = new JLabel("tMax service ");
        maxiSTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxiSTime.setBounds(30, 221, 200, 30);
        contentPane.add(maxiSTime);

        clienti = new JTextField();
        clienti.setBounds(30, 54, 96, 19);
        contentPane.add(clienti);
        clienti.setColumns(10);

       cozi = new JTextField();
        cozi.setColumns(10);
        cozi.setBounds(238, 54, 96, 19);
        contentPane.add(cozi);

        intervale = new JTextField();
        intervale.setColumns(10);
        intervale.setBounds(30, 123, 96, 19);
        contentPane.add(intervale);

        minArrv = new JTextField();
        minArrv.setColumns(10);
        minArrv.setBounds(238, 123, 96, 19);
        contentPane.add(minArrv);

        maxArrv = new JTextField();
        maxArrv.setColumns(10);
        maxArrv.setBounds(30, 192, 96, 19);
        contentPane.add( maxArrv);

        minServ = new JTextField();
        minServ.setColumns(10);
        minServ.setBounds(238, 192, 96, 19);
        contentPane.add( minServ);

        maxServ = new JTextField();
        maxServ.setColumns(10);
        maxServ.setBounds(30, 253, 96, 19);
        contentPane.add(maxServ);

        start = new JButton("START");
        start.setBackground(new Color(102, 153, 102));
        start.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        start.setBounds(249, 251, 85, 21);
        contentPane.add(start);

        textArea = new JTextArea();

        textArea.setBounds(30, 299, 328, 91);
        contentPane.add(textArea);
    }

    public JTextField getClients() {
        return clienti;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(String s) {
        this.textArea.setText(s);
        contentPane.add(textArea);
    }

    public JTextField getQueues() {
        return cozi;
    }

    public JTextField getIntervalS() {
        return intervale;
    }

    public JTextField getMiniArrival() {
        return minArrv;
    }

    public JTextField getMaxiArrival() {
        return maxArrv;
    }

    public JTextField getMiniService() {
        return  minServ;
    }

    public JTextField getMaxiService() {
        return  maxServ;
    }


}





