package service;

import database.Dao;
import exception.UnsupportedFileFormatException;
import model.Customer;
import reader.CSVReaderImpl;
import reader.Reader;
import reader.XMLReaderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DBWriter {

    private static DBWriter ourInstance = new DBWriter();

    public static DBWriter getInstance() {
        return ourInstance;
    }

    private DBWriter() {
    }

    // review to powinny być "wstrzykiwane" zależności. O ile w Main.java to jeszcze uszło, o tyle dao NIE MOŻE BYC singletonem (transakcje)
    Dao dao = Dao.getInstance();
    // review skąd przypadłość tworzenia konstruktorów prywatnych i metody statycznej getInstance(). DLaczego ograniczamy się do jednego obiektu? KOd nie jest otwarty.
    Reader csv = CSVReaderImpl.getInstance();
    // review dodatkowo - trudno jest testować taki kod. Jak zamockować metodę statyczną. Tak, wiem, że są PawerMOcki, ale... w nowszych frameworkach już nie działają
    Reader xml = XMLReaderImpl.getInstance();

    // review metoda powinna robić to, co ma w nazwie - ta nie tylko zapisuje do bazy danych, ale robi całą logikę biznesową czyta i zapisuje
    public void saveToDB() {
        List<Customer> customers = getCustomerList();

        for (Customer customer : customers) {
            dao.insertNewRecord(customer);
        }
    }

    private List<Customer> getCustomerList() {
        List<Customer> customers = new ArrayList<>();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the path to the file (CSV or XML) you want to save in the database:");
            String filePath = scanner.next();
            // review co się stanie jak użytkownik wpisze jeden znak?
            String fileFormat = filePath.substring(filePath.length() - 3);
            // review powino być: "csv".equals(fileFormat)
            if (fileFormat.equals("csv")) {
                customers = csv.getAllCustomers(filePath);
            } else if (fileFormat.equals("xml")) {
                customers = xml.getAllCustomers(filePath);
            } else {
                throw new UnsupportedFileFormatException("The file is in the wrong format.");
            }
            // review aaggrrrhhh!!! Rzucamy wyjątek, żeby go 4 linie niżej złapać???? Po co?
        } catch (UnsupportedFileFormatException e) {
            e.printStackTrace();
        }
        return customers;
    }

}
