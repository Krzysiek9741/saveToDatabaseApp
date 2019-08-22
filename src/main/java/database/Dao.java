package database;

import model.Contact;
import model.Customer;

import java.sql.*;

public class Dao {
    private static Dao ourInstance = new Dao();

    public static Dao getInstance() {
        return ourInstance;
    }

    private Dao() {
        openConnection();
    }

    //final static String url = "jdbc:mysql://localhost/saver?serverTimezone=Europe/Warsaw";
    final static String url = "jdbc:mysql://remotemysql.com/OuT4FC22XG?serverTimezone=Europe/Warsaw";
    //final static String user = "root";
    final static String user = "OuT4FC22XG";
    //final static String pass = "Lamus321";
    final static String pass = "qMiHt3kQII";
    static Connection conn;

    private void openConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertNewRecord(Customer customer) {
        try {
            String insertCustomer = "insert into customers (name , surname, age) values (? , ?, ?) ";
            PreparedStatement pst = conn.prepareStatement(insertCustomer);
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getSurname());
            if (customer.getAge() != null) {
                pst.setInt(3, customer.getAge());
            } else {
                pst.setNull(3, 4);
            }

            pst.execute();

            String insertContact = "insert into contacts (id_customer , type, contact) values (? , ?, ?) ";
            PreparedStatement pst2;
            for (Contact contact : customer.getContacts()) {
                pst2 = conn.prepareStatement(insertContact);
                pst2.setLong(1, findLastCustomerId());
                pst2.setInt(2, contact.getContactType().ordinal());
                pst2.setString(3, contact.getContact());
                pst2.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Long findLastCustomerId() {
        Long customerId = null;
        try {
            String maxIdQuery = "select max(id) from customers";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(maxIdQuery);
            if (rs.next()) {
                customerId = rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerId;
    }

    @Override
    protected void finalize() throws Throwable {
        closeConnection();
        super.finalize();
    }
}
