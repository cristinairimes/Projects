package ro.utcn.cristina.model;

import java.util.ArrayList;

public class Order {
    private int idOrder;
    private int quantity;
    private String nameClient;
    private String nameProduct;
    private Float total;


    public Order(int quantity, String idClient, String product) {
        this.quantity = quantity;
        this.nameProduct = product;
        this.nameClient = idClient;
    }

    public Order() {

    }


    public Order(int idOrder, String nameClient, String product, int quantity, Float price) {
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.nameProduct = product;
        this.nameClient = nameClient;
        this.total = price;
    }


    public Order(String nameClient, String product, int quantity, Float price) {
        this.idOrder = idOrder;
        this.quantity = quantity;
        this.nameProduct = product;
        this.nameClient = nameClient;
        this.total = price;
    }

    public Order(int idOrder, Float total) {
        this.idOrder = idOrder;
        this.total = total;
    }


    public Float getTotal() {
        return total;
    }


    public void setTotal(Float total) {
        this.total = total;
    }


    public int getIdOrder() {
        return idOrder;
    }

       public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }


    public int getQuantity() {
        return quantity;
    }


    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public String getClient() {
        return nameClient;
    }


    public void setClient(String idClient) {
        this.nameClient = idClient;
    }


    public String getProduct() {
        return nameProduct;
    }


    public void setIdProduct(String product) {
        this.nameProduct = product;
    }
}