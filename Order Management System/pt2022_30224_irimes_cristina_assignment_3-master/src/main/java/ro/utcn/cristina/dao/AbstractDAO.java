package ro.utcn.cristina.dao;

import ro.utcn.cristina.connection.ConnectionFactory;

import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AbstractDAO<T> {

    private final Class<T> type;
    Integer element = 0;

    @SuppressWarnings({"unchecked", "unchecked"})


    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }


    private String createSelectQuery(String fieldToSelectBy) {
        StringBuilder sel = new StringBuilder();
        sel.append("SELECT * FROM "+type.getSimpleName()+" WHERE " + fieldToSelectBy + " =?");
        return sel.toString();
    }


    public T findById(int searchedId) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        String query = createSelectQuery("id");
        Connection connection= ConnectionFactory.getConnection();
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, searchedId);
        ResultSet resultSet = statement.executeQuery();
        ConnectionFactory.close(connection);
        return returnObjects(resultSet).get(0);
    }



    public List<T> findAll() throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        StringBuilder sel = new StringBuilder();
        sel.append("SELECT * FROM "+type.getSimpleName().toLowerCase());
        String query = sel.toString();
        Connection connection = ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ConnectionFactory.close(connection);
        return returnObjects(resultSet);
    }


    private String createUpdateQuery() {
        StringBuilder sel = new StringBuilder();
        sel.append("UPDATE "+type.getSimpleName().toLowerCase()+" SET ");
        for (Field f : type.getDeclaredFields()) {
            sel.append(f.getName() + " = ?,");
        }
        sel.deleteCharAt(sel.length() - 1);
        sel.append("WHERE id=?");
        return sel.toString();
    }



    public T update(T t) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        String query = createUpdateQuery();
        Connection connection =  ConnectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        int i = 1;
        for (Field f : t.getClass().getDeclaredFields()) {
            f.setAccessible(true);
            Object value = f.get(t);
            statement.setObject(i, value);
            i++;
            f.setAccessible(false);
        }
        ConnectionFactory.close(connection);
        return t;
    }



    private String createDeleteQuery(String field) {
        StringBuilder sel = new StringBuilder();
        sel.append("DELETE FROM " + type.getSimpleName().toLowerCase()+" WHERE " + field + " =?");
        return sel.toString();
    }


    public int delete(T t) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        Field f = t.getClass().getDeclaredFields()[0];
        f.setAccessible(true);
        String query = createDeleteQuery(f.getName());
        Connection connection = ConnectionFactory.getConnection();;
        PreparedStatement statement =  connection.prepareStatement(query);
        statement.setInt(1, (int) f.get(t));
        int deletedItem = statement.executeUpdate();
        ConnectionFactory.close(connection);
        return deletedItem;
    }




    private String createInsertQuery() {
        StringBuilder sel = new StringBuilder();
        sel.append("INSERT INTO "+type.getSimpleName().toLowerCase()+" VALUES ( ");
        for (Field f : type.getDeclaredFields()) {
            sel.append("?,");
        }
        sel.deleteCharAt(sel.length() - 1);
        sel.append(")");
        return sel.toString();
    }



    public T insert(T t) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        String query = createInsertQuery();
        Connection connection = ConnectionFactory.getConnection();;
        PreparedStatement statement = connection.prepareStatement(query);
        int i = 1;
        for (Field f : t.getClass().getDeclaredFields()) {
            Object value = f.get(t);
            statement.setObject(i, value);
            i++;
        }
        ConnectionFactory.close(connection);
        return null;
    }



    private List<T> returnObjects(ResultSet resultSet) throws SQLException, IllegalAccessException, InvocationTargetException, InstantiationException, IntrospectionException {
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            Constructor constructor = constructors[i];
            if (constructor.getGenericParameterTypes().length == 0) {
                break;
            }
        }
        Constructor constructor=null;
        while (resultSet.next()) {
            constructor.setAccessible(true);
            T instance = (T) constructor.newInstance();
            for (Field field : type.getDeclaredFields()) {
                String fieldName = field.getName();
                Object value = resultSet.getObject(fieldName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getWriteMethod();
                method.invoke(instance, value);
            }
            list.add(instance);
        }
        return list;
    }



    public void getMyTable(DefaultTableModel m, ArrayList<T> myList) {
        m = new DefaultTableModel();
        Field[] myFields = type.getDeclaredFields();

        Integer columns = type.getDeclaredFields().length;
        String[] colNames = new String[columns];
        Object[][] content = new Object[myList.size()][columns];

        for (T myElement = myList.get(element); element < myList.size(); element++) {
            int j = 0;
            for (Field field : myFields) {
                PropertyDescriptor propertyDescriptor = null;
                try {
                    propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                } catch (IntrospectionException e) {
                    e.printStackTrace();
                }
                Object value = null;
                try {
                    value = propertyDescriptor.getReadMethod().invoke(myElement);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                content[element][j] = value;
                j++;

            }
        }


    }

}