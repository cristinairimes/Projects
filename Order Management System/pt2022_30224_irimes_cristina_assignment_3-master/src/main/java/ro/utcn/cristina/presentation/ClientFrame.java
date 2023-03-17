package ro.utcn.cristina.presentation;

import ro.utcn.cristina.connection.ConnectionFactory;
import ro.utcn.cristina.dao.ClientDAO;
import ro.utcn.cristina.model.Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;

public class ClientFrame {
    Frame clientFrame;
    JPanel panel;
    JButton newClientBtn;
    JButton editClientBtn;
    JButton deleteClientBtn;
    JButton viewClientsBtn;

    JLabel name;
    JLabel address;
    JLabel email;
    JLabel id;

    JTextField nameTextField;
    JTextField addressTextField;
    JTextField emailTextField;
    JTextField idTextField;

    JTable tableForMyClients;
    Connection myConnection;
    DefaultTableModel modelForClientsTable;

    ClientDAO client = new ClientDAO();

    public ClientFrame() throws IllegalAccessException, IntrospectionException, InvocationTargetException, SQLException {
        myConnection = ConnectionFactory.getConnection();

        clientFrame = new JFrame();
        clientFrame.setSize(1530, 830);
        clientFrame.setTitle("CLIENT FRAME");
        clientFrame.setResizable(false);
        clientFrame.setLayout(null);

        Border blueLine = BorderFactory.createLineBorder(new Color(0, 156, 204), 5);
        Border blueLin = BorderFactory.createLineBorder(new Color(0, 156, 204), 2);

        panel = new JPanel();
        panel.setSize(500, 530);
        panel.setBounds(150, 50, 1200, 700);
        panel.setBackground(new Color(230, 255, 249));
        panel.setBorder(blueLin);
        panel.setLayout(null);
        panel.setVisible(true);
        clientFrame.add(panel);


        viewClientsBtn = new JButton("VIEW CLIENTS");
        viewClientsBtn.setBounds(100, 410, 300, 70);
        viewClientsBtn.setBackground(Color.WHITE);
        viewClientsBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        viewClientsBtn.setForeground(new Color(0, 156, 204));
        viewClientsBtn.setFocusable(false);
        viewClientsBtn.setBorder(blueLin);
        viewClientsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == viewClientsBtn) {

                    clientFrame.dispose();
                    try {
                        ClientFrame newCf = new ClientFrame();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IntrospectionException introspectionException) {
                        introspectionException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });
        panel.add(viewClientsBtn);

        editClientBtn = new JButton("EDIT CLIENT");
        editClientBtn.setBounds(100, 200, 300, 70);
        editClientBtn.setBackground(Color.WHITE);
        editClientBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        editClientBtn.setForeground(new Color(0, 156, 204));
        editClientBtn.setFocusable(false);
        editClientBtn.setBorder(blueLin);
        editClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editClientBtn) {
                    try {
                        client.editClient(Integer.parseInt(idTextField.getText()), nameTextField.getText(), addressTextField.getText(), emailTextField.getText());
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        panel.add(editClientBtn);

        newClientBtn = new JButton("NEW CLIENT");
        newClientBtn.setBounds(100, 90, 300, 70);
        newClientBtn.setBackground(Color.WHITE);
        newClientBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        newClientBtn.setForeground(new Color(0, 156, 204));
        newClientBtn.setFocusable(false);
        newClientBtn.setBorder(blueLin);
        newClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == newClientBtn) {
                    Client clientt = new Client(nameTextField.getText(), addressTextField.getText(), emailTextField.getText());
                    try {
                        client.insertClient(clientt);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }

            }
        });
        panel.add(newClientBtn);


        deleteClientBtn = new JButton("DELETE CLIENT");
        deleteClientBtn.setBounds(100, 310, 300, 70);
        deleteClientBtn.setBackground(Color.WHITE);
        deleteClientBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        deleteClientBtn.setForeground(new Color(0, 156, 204));
        deleteClientBtn.setFocusable(false);
        deleteClientBtn.setBorder(blueLin);
        deleteClientBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == deleteClientBtn) {

                    String n = nameTextField.getText();
                    try {
                        client.deleteClient(n);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    clientFrame.dispose();
                    try {
                        ClientFrame v = new ClientFrame();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IntrospectionException introspectionException) {
                        introspectionException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        panel.add(deleteClientBtn);


        name = new JLabel("NAME");
        name.setBounds(50, 510, 100, 40);
        name.setFont(new Font("Monospaced", Font.BOLD, 30));
        name.setForeground(new Color(0, 156, 204));
        panel.add(name);

        nameTextField = new JTextField();
        nameTextField.setBounds(30, 570, 180, 20);
        nameTextField.setBorder(blueLin);
        panel.add(nameTextField);

        address = new JLabel("AdDRESS");
        address.setBounds(250, 510, 200, 40);
        address.setFont(new Font("Monospaced", Font.BOLD, 30));
        address.setForeground(new Color(0, 156, 204));
        panel.add(address);

        addressTextField = new JTextField();
        addressTextField.setBounds(230, 570, 180, 20);
        addressTextField.setBorder(blueLin);
        panel.add(addressTextField);

        email = new JLabel("EMAIL");
        email.setBounds(450, 510, 200, 40);
        email.setFont(new Font("Monospaced", Font.BOLD, 30));
        email.setForeground(new Color(0, 156, 204));
        panel.add(email);

        emailTextField = new JTextField();
        emailTextField.setBounds(430, 570, 180, 20);
        emailTextField.setBorder(blueLin);
        panel.add(emailTextField);


        id = new JLabel("ID");
        id.setBounds(250, 610, 200, 40);
        id.setFont(new Font("Monospaced", Font.BOLD, 30));
        id.setForeground(new Color(0, 156, 204));
        panel.add(id);

        idTextField = new JTextField();
        idTextField.setBounds(230, 670, 180, 20);
        idTextField.setBorder(blueLin);
        panel.add(idTextField);



        modelForClientsTable = new DefaultTableModel();
        client.getMyTable(modelForClientsTable, client.listAllClients());
        modelForClientsTable.addColumn("Id");
        modelForClientsTable.addColumn("Name");
        modelForClientsTable.addColumn("email");
        modelForClientsTable.addColumn("adress");


        tableForMyClients = new JTable(modelForClientsTable);
        tableForMyClients.setBounds(530, 100, 600, 350);
        tableForMyClients.setBackground(new Color(230, 255, 249));
        tableForMyClients.setBackground(Color.WHITE);
        tableForMyClients.setFont(new Font("MV Boly", Font.BOLD, 15));
        tableForMyClients.setForeground(new Color(0, 156, 204));
        tableForMyClients.setBorder(blueLin);
        panel.add(tableForMyClients);
        try {
            //Client c:client.listAllClients()
            for (int i = 0; i < client.listAllClients().size(); i++) {
                Client c = client.listAllClients().get(i);
                String nameString = String.valueOf(c.getName());
                String idString = String.valueOf(c.getId());
                String emailString = String.valueOf(c.getEmail());
                String addressString = String.valueOf(c.getAddress());
                modelForClientsTable.addRow(new Object[]{idString, nameString, addressString, emailString});
                i++;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        clientFrame.setVisible(true);
    }
}