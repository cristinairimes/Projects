package ro.utcn.cristina.presentation;
import ro.utcn.cristina.connection.ConnectionFactory;
import ro.utcn.cristina.dao.OrderDAO;
import ro.utcn.cristina.dao.ProductDAO;
import ro.utcn.cristina.model.Order;
import ro.utcn.cristina.model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;



public class OrderFrame {
    Frame ordFrame;
    JPanel panel;


    JButton addOrdBtn;
    JButton viewOrd;


    JLabel quantity;
    JLabel clientName;
    JLabel price;



    JTextField nameTextField;
    JTextField quantityTextField;
    JTextField priceTextField;
    JTextField clientNameTextField;



    JTable ordTable;
    DefaultTableModel modelForOrdersTable;
    Connection myConnection;
    OrderDAO ord = new OrderDAO();
    ProductDAO prd = new ProductDAO();

    public OrderFrame() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        myConnection = ConnectionFactory.getConnection();
        ordFrame = new JFrame();
        ordFrame.setSize(1530, 830);
        ordFrame.setTitle("CLIENT FRAME");
        ordFrame.setResizable(false);
        ordFrame.setLayout(null);

        Border blueLine = BorderFactory.createLineBorder(new Color(0, 156, 204), 5);
        Border blueLin = BorderFactory.createLineBorder(new Color(0, 156, 204), 2);
        panel = new JPanel();
        panel.setSize(500, 530);
        panel.setBounds(150, 50, 1200, 700);
        panel.setBackground(new Color(230, 255, 249));
        panel.setLayout(null);
        panel.setVisible(true);
        panel.setBorder(blueLin);
        ordFrame.add(panel);


        viewOrd = new JButton("VIEW ORDERS");
        viewOrd.setBounds(100, 90, 300, 70);
        viewOrd.setBackground(Color.WHITE);
        viewOrd.setFont(new Font("Monospaced", Font.BOLD, 30));
        viewOrd.setForeground(new Color(0, 156, 204));
        viewOrd.setFocusable(false);
        viewOrd.setBorder(blueLin);
        viewOrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == viewOrd) {
                    ordFrame.dispose();
                    try {
                        OrderFrame newOrderFrame = new OrderFrame();
                    } catch (IllegalAccessException illegalAccessException) {
                        illegalAccessException.printStackTrace();
                    } catch (IntrospectionException introspectionException) {
                        introspectionException.printStackTrace();
                    } catch (InvocationTargetException invocationTargetException) {
                        invocationTargetException.printStackTrace();
                    }
                }

            }
        });
        panel.add(viewOrd);

        addOrdBtn = new JButton("ADD ORDER");
        addOrdBtn.setBounds(100, 200, 300, 70);
        addOrdBtn.setBackground(Color.WHITE);
        addOrdBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        addOrdBtn.setForeground(new Color(0, 156, 204));
        addOrdBtn.setBorder(blueLin);
        addOrdBtn.setFocusable(false);
        addOrdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addOrdBtn) {


                    String numeProd = nameTextField.getText();
                    Integer cantitate = Integer.parseInt(quantityTextField.getText());

                    Product pp = null;
                    try {
                        pp = prd.findByName(numeProd);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    if (pp == null) {
                        JOptionPane.showMessageDialog(null, "This product does not exist!");
                    }
                    if (pp.getQuantity() < cantitate) {
                        JOptionPane.showMessageDialog(null, "There are no sufficient products in stock!");
                    } else {

                        pp.setQuantity(pp.getQuantity() - cantitate);
                    }
                    Order o = new Order(clientNameTextField.getText(), nameTextField.getText(), Integer.parseInt(quantityTextField.getText()), Float.parseFloat(priceTextField.getText()));
                    try {
                        ord.insertMyOrder(o);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                }
            }
        });
        panel.add(addOrdBtn);




        clientName = new JLabel("CLIENT NAME");
        clientName.setBounds(80, 410, 400, 40);
        clientName.setFont(new Font("Monospaced", Font.BOLD, 30));
        clientName.setForeground(new Color(0, 156, 204));
        panel.add(clientName);

        clientNameTextField = new JTextField();
        clientNameTextField.setBounds(80, 470, 180, 20);
        panel.add(clientNameTextField);
        clientNameTextField.setBorder(blueLin);


        price = new JLabel("NAME");
        price.setBounds(50, 510, 100, 40);
        price.setFont(new Font("Monospaced", Font.BOLD, 30));
        price.setForeground(new Color(0, 156, 204));
        panel.add(price);

        nameTextField = new JTextField();
        nameTextField.setBounds(30, 570, 180, 20);
        panel.add(nameTextField);
        nameTextField.setBorder(blueLin);

        quantity = new JLabel("QTY");
        quantity.setBounds(250, 510, 200, 40);
        quantity.setFont(new Font("Monospaced", Font.BOLD, 30));
        quantity.setForeground(new Color(0, 156, 204));
        panel.add(quantity);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(230, 570, 180, 20);
        panel.add(quantityTextField);
        quantityTextField.setBorder(blueLin);

        price = new JLabel("PRICE");
        price.setBounds(450, 510, 200, 40);
        price.setFont(new Font("Monospaced", Font.BOLD, 30));
        price.setForeground(new Color(0, 156, 204));
        panel.add(price);

        priceTextField = new JTextField();
        priceTextField.setBounds(430, 570, 180, 20);
        panel.add(priceTextField);
        priceTextField.setBorder(blueLin);

        // Column Names
        modelForOrdersTable = new DefaultTableModel();
        modelForOrdersTable.addColumn("Id");
        modelForOrdersTable.addColumn("Name");
        modelForOrdersTable.addColumn("ProductName");
        modelForOrdersTable.addColumn("Quantity");
        modelForOrdersTable.addColumn("Price");



        ordTable = new JTable(modelForOrdersTable);
        ordTable.setBounds(530, 100, 600, 350);
        ordTable.setBackground(new Color(230, 255, 249));
        ordTable.setBackground(Color.WHITE);
        ordTable.setFont(new Font("MV Boly", Font.BOLD, 15));
        ordTable.setForeground(new Color(0, 156, 204));
        ordTable.setBorder(blueLin);
        panel.add(ordTable);
        try {
            for (Order c : ord.listAllOrders()) {
                String idString = String.valueOf(c.getIdOrder());
                String clientNameString = String.valueOf(c.getClient());
                String productString = String.valueOf(c.getProduct());
                String quantityString = String.valueOf(c.getQuantity());
                String totalString = String.valueOf(c.getTotal());
                modelForOrdersTable.addRow(new Object[]{idString, clientNameString, productString, quantityString, totalString});
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ordFrame.setVisible(true);
    }

}