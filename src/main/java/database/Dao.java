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

    final static String url = "jdbc:mysql://remotemysql.com/OuT4FC22XG?serverTimezone=Europe/Warsaw";
    final static String user = "OuT4FC22XG";
    final static String pass = "qMiHt3kQII";
    static Connection conn;

    //login to the database at: https://remotemysql.com/phpmyadmin/index.php

    private void openConnection() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // review dalczego public?
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertNewRecord(Customer customer) {

        // review jak jest kontrakt zabezpieczoy? A co jeżeli customer == null?

        // review ta metoda wywoływana jest w pętli tyle razy, ile mamy rekordów klienta...
        try {
            // review ... wiem, że jest pula Stringów, ale dlaczego nie "wyrzucić" wyżej zapytania SQL - tutaj przydało by się private static field
            String insertCustomer = "insert into customers (name , surname, age) values (? , ?, ?) ";
            PreparedStatement pst = conn.prepareStatement(insertCustomer);
            pst.setString(1, customer.getName());
            pst.setString(2, customer.getSurname());
            if (customer.getAge() != null) {
                pst.setInt(3, customer.getAge());
            } else {
                // review wartość 4 jest zaciemnia kod ekstremalnie! Co ona oznacza? Dlacegeo nie dać Types.INTEGER - i nie ma zastanawiania się?
                pst.setNull(3, 4);
            }

            // review jeżeli tutaj wyleci wyjątek SQL, to jak zostanie zamknięte connection i pst
            pst.execute();

            String insertContact = "insert into contacts (id_customer , type, contact) values (? , ?, ?) ";
            // review dlaczego deklaracja zmiennej jest rozdzielona od jej inicjalizacji (w pętli for)?
            PreparedStatement pst2;
            for (Contact contact : customer.getContacts()) {
                pst2 = conn.prepareStatement(insertContact);
                pst2.setLong(1, findLastCustomerId()); // review inaczej pobierałbym ostatnio dodanego klienta
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
            // review najwyższa wartość ID to wcale nie musi być ostatnio dodany rekord! A co jeżeli ktoś inny doda innym programem, przez WWW? W międzyczasie
            // + i już podepniemy kontakt pod nieprawidłową osobę. TO zadziała wyłącznie, koedy zawłaszczymy bazę dla siebie (i nie zmienimy wartości autonumerowania)
            // + proponuję: https://docs.oracle.com/javase/8/docs/api/java/sql/Statement.html#getGeneratedKeys--
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


    // review z dokumentacji metody finalize()
//    Called by the garbage collector on an object when garbage collection
//     * determines that there are no more references to the object.

    // review to teraz pytanie - kiedy zamknie się to połączenie? Kiedy metoda ta zostanie wywołana, jeżeli to jest "singleton"?
    @Override
    protected void finalize() throws Throwable {
        closeConnection();
        super.finalize();
    }
}
