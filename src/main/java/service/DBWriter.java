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

    Dao dao = Dao.getInstance();
    Reader csv = CSVReaderImpl.getInstance();
    Reader xml = XMLReaderImpl.getInstance();

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
            String fileFormat = filePath.substring(filePath.length() - 3);
            if (fileFormat.equals("csv")) {
                customers = csv.getAllCustomers(filePath);
            } else if (fileFormat.equals("xml")) {
                customers = xml.getAllCustomers(filePath);
            } else {
                throw new UnsupportedFileFormatException("The file is in the wrong format.");
            }
        } catch (UnsupportedFileFormatException e) {
            e.printStackTrace();
        }
        return customers;
    }
}
