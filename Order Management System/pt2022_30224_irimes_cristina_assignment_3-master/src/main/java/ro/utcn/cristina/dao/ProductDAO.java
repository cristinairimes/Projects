package ro.utcn.cristina.dao;

import ro.utcn.cristina.connection.ConnectionFactory;
import ro.utcn.cristina.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.Random;


public class ProductDAO extends AbstractDAO<Product> {
    private static final String insertProduct = "INSERT INTO product (id,name,quantity,price)" + " VALUES (?,?,?,?)";
    private final static String findStatement = "SELECT * FROM product where name = ?";



    public Product findByName(String nameProduct) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = dbConnection.prepareStatement(ProductDAO.findStatement);
        findStatement.setString(1, nameProduct);
        ResultSet result = findStatement.executeQuery();
        result.next();
        int price = result.getInt("price");
        int quantity = result.getInt("quantity");
        Product searchedProduct = new Product(nameProduct, price, quantity);
        ConnectionFactory.close(dbConnection);
        return searchedProduct;
    }



    public ArrayList<Product> listAllProducts() throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        ArrayList<Product> listOfAllPrroducts = new ArrayList<Product>();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product");
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            int idProduct = result.getInt("id");
            String productNameStr = result.getString("name");
            String quantityStr = result.getString("quantity");
            int quantityParsed = Integer.parseInt(quantityStr);
            String priceStr = result.getString("price");
            float priceParced = Float.parseFloat(priceStr);
            Product productFromList = new Product();
            productFromList.setQuantity(quantityParsed);
            productFromList.setPrice(priceParced);
            productFromList.setIdProduct(idProduct);
            productFromList.setProductName(productNameStr);
            listOfAllPrroducts.add(productFromList);
        }
        ConnectionFactory.close(connection);
        return listOfAllPrroducts;
    }


    public int insertProduct(Product p) throws SQLException {
        Random random = new Random();
        int nr = random.nextInt(50);
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(insertProduct, Statement.RETURN_GENERATED_KEYS);
        statement.setInt(1, nr);
        statement.setString(2, p.getProductName());
        statement.setInt(3, p.getQuantity());
        statement.setFloat(4, p.getPrice());
        statement.executeUpdate();
        ConnectionFactory.close(connection);
        return nr;
    }


    public void deleteProduct(Integer productId) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE id = ?");
        statement.setInt(1, productId);
        statement.executeUpdate();
        ConnectionFactory.close(connection);
    }


    public void editProduct(int id, String name, Integer quantity, Float price) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = statement = connection.prepareStatement("UPDATE product SET name = ? WHERE id = ?");
        statement.setString(1, name);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement = connection.prepareStatement("UPDATE product SET quantity = ? WHERE id = ?");
        statement.setInt(1, quantity);
        statement.setInt(2, id);
        statement.executeUpdate();
        statement = connection.prepareStatement("UPDATE product SET price = ? WHERE id = ?");
        statement.setFloat(1, price);
        statement.setInt(2, id);
        statement.executeUpdate();
        ConnectionFactory.close(connection);
    }


    public int findQuantity(String name) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();;
        PreparedStatement statement =  connection.prepareStatement("SELECT cantitate FROM product WHERE name = ?;");
        statement.setString(1, name);
        ResultSet  rs = statement.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(3));
            rs.getInt(3);
        }
        ConnectionFactory.close(connection);
        ConnectionFactory.close(rs);
        ConnectionFactory.close(statement);

        return 0;
    }

}
