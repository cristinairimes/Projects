package ro.utcn.cristina.presentation;
import ro.utcn.cristina.connection.ConnectionFactory;
import ro.utcn.cristina.dao.ProductDAO;
import ro.utcn.cristina.model.Product;

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


public class ProductFrame {
    Frame prodsFrame;
    JPanel panel;


    JButton newProdBtn;
    JButton editProdBtn;
    JButton deleteProdBtn;
    JButton viewProdBtn;


    JLabel id;
    JLabel productName;
    JLabel quantity;
    JLabel price;

    JTextField idTextField;
    JTextField priceTextField;
    JTextField quantityTextField;
    JTextField nameTextField;


    JTable productsTable;
    DefaultTableModel modelForProductsTable;
    Connection myConnection;
    ProductDAO pr = new ProductDAO();

    public ProductFrame() throws IllegalAccessException, IntrospectionException, InvocationTargetException, SQLException {
        myConnection = ConnectionFactory.getConnection();

        prodsFrame = new JFrame();
        prodsFrame.setSize(1530, 830);
        prodsFrame.setTitle("PRODUCT FRAME");
        prodsFrame.setResizable(false);
        prodsFrame.setLayout(null);

        Border blueline = BorderFactory.createLineBorder(new Color(0, 156, 204), 5);
        Border bluln = BorderFactory.createLineBorder(new Color(0, 156, 204), 2);

        panel = new JPanel();
        panel.setSize(500, 530);
        panel.setBounds(150, 50, 1200, 700);
        panel.setBackground(new Color(230, 255, 249));
        panel.setLayout(null);
        panel.setVisible(true);
        prodsFrame.add(panel);

        newProdBtn = new JButton("NEW PRODUCT");
        newProdBtn.setBounds(100, 90, 300, 70);
        newProdBtn.setBackground(Color.WHITE);
        newProdBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        newProdBtn.setForeground(new Color(0, 156, 204));
        newProdBtn.setFocusable(false);
        newProdBtn.setBorder(bluln);
        newProdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == newProdBtn) {
                    Product p = new Product(nameTextField.getText(), Integer.parseInt(quantityTextField.getText()), Float.parseFloat(priceTextField.getText()));
                    try {
                        pr.insertProduct(p);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        panel.add(newProdBtn);

        editProdBtn = new JButton("EDIT PRODUCT");
        editProdBtn.setBounds(100, 200, 300, 70);
        editProdBtn.setBackground(Color.WHITE);
        editProdBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        editProdBtn.setForeground(new Color(0, 156, 204));
        editProdBtn.setFocusable(false);
        editProdBtn.setBorder(bluln);
        editProdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == editProdBtn) {

                    try {
                        pr.editProduct(Integer.parseInt(idTextField.getText()), nameTextField.getText(), Integer.parseInt(quantityTextField.getText()), Float.parseFloat(priceTextField.getText()));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });
        panel.add(editProdBtn);


        deleteProdBtn = new JButton("DELETE PRODUCT");
        deleteProdBtn.setBounds(100, 310, 300, 70);
        deleteProdBtn.setBackground(Color.WHITE);
        deleteProdBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        deleteProdBtn.setForeground(new Color(0, 156, 204));
        deleteProdBtn.setFocusable(false);
        deleteProdBtn.setBorder(bluln);
        deleteProdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == deleteProdBtn) {
                    String n = idTextField.getText();
                    try {
                        pr.deleteProduct(Integer.parseInt(n));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    prodsFrame.dispose();
                    try {
                        ProductFrame v = new ProductFrame();
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
        panel.add(deleteProdBtn);

        viewProdBtn = new JButton("VIEW PRODUCTS");
        viewProdBtn.setBounds(100, 410, 300, 70);
        viewProdBtn.setBackground(Color.WHITE);
        viewProdBtn.setFont(new Font("Monospaced", Font.BOLD, 30));
        viewProdBtn.setForeground(new Color(0, 156, 204));
        viewProdBtn.setFocusable(false);
        viewProdBtn.setBorder(bluln);
        viewProdBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == viewProdBtn) {
                    prodsFrame.dispose();
                    try {
                        ProductFrame newProdFrame = new ProductFrame();
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
        panel.add(viewProdBtn);

        id = new JLabel("ID");
        id.setBounds(250, 610, 200, 40);
        id.setFont(new Font("Monospaced", Font.BOLD, 30));
        id.setForeground(new Color(0, 156, 204));
        panel.add(id);

        idTextField = new JTextField();
        idTextField.setBounds(230, 670, 180, 20);
        idTextField.setBorder(bluln);
        panel.add(idTextField);
        prodsFrame.setVisible(true);


        productName = new JLabel("NAME");
        productName.setBounds(50, 510, 100, 40);
        productName.setFont(new Font("Monospaced", Font.BOLD, 30));
        productName.setForeground(new Color(0, 156, 204));
        panel.add(productName);

        nameTextField = new JTextField();
        nameTextField.setBounds(30, 570, 180, 20);
        nameTextField.setBorder(bluln);
        panel.add(nameTextField);

        quantity = new JLabel("QUANTITY");
        quantity.setBounds(250, 510, 200, 40);
        quantity.setFont(new Font("Monospaced", Font.BOLD, 30));
        quantity.setForeground(new Color(0, 156, 204));
        panel.add(quantity);

        quantityTextField = new JTextField();
        quantityTextField.setBounds(230, 570, 180, 20);
        panel.add(quantityTextField);
        quantityTextField.setBorder(bluln);

        price = new JLabel("PRICE");
        price.setBounds(450, 510, 200, 40);
        price.setFont(new Font("Monospaced", Font.BOLD, 30));
        price.setForeground(new Color(0, 156, 204));
        panel.add(price);

        priceTextField = new JTextField();
        priceTextField.setBounds(430, 570, 180, 20);
        priceTextField.setBorder(bluln);
        panel.add(priceTextField);


        modelForProductsTable = new DefaultTableModel();
        pr.getMyTable(modelForProductsTable, pr.listAllProducts());
        modelForProductsTable.addColumn("Id");
        modelForProductsTable.addColumn("Name");
        modelForProductsTable.addColumn("email");
        modelForProductsTable.addColumn("adress");

        productsTable = new JTable(modelForProductsTable);
        try {
            for (Product p : pr.listAllProducts()) {
                String id = String.valueOf(p.getIdProduct());
                String name = String.valueOf(p.getProductName());
                String quan = String.valueOf(p.getQuantity());
                String price = String.valueOf(p.getPrice());
                modelForProductsTable.addRow(new Object[]{id, name, quan, price});
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        productsTable.setBounds(530, 100, 600, 350);
        productsTable.setBackground(new Color(230, 255, 249));
        productsTable.setBackground(Color.WHITE);
        productsTable.setFont(new Font("MV Boly", Font.BOLD, 15));
        productsTable.setForeground(new Color(0, 156, 204));
        productsTable.setBorder(bluln);

        panel.add(productName);
        panel.setBorder(bluln);
        panel.add(productsTable);


    }
}