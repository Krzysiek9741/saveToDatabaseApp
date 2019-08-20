package reader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.Contact;
import model.Customer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderImpl {
    private static CSVReaderImpl ourInstance = new CSVReaderImpl();

    public static CSVReaderImpl getInstance() {
        return ourInstance;
    }

    private CSVReaderImpl() {
    }

    private List<String[]> getAllData(String filePath) {
        Path path = Paths.get(filePath);
        List<String[]> allRecords = new ArrayList<>();
        try (
                BufferedReader reader = Files.newBufferedReader(path);
                CSVReader csvReader = new CSVReaderBuilder(reader).build()
        ) {

            allRecords = csvReader.readAll();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRecords;
    }

    public List<Customer> getAllCustomers(String filePath){
        List<String[]> allRecords = getAllData(filePath);
        List<Customer> allCustomers = new ArrayList<>();
        for (String[] record:allRecords) {
            String name = record[0];
            String surname = record[1];
            Customer customer = new Customer(name, surname);
            if (!record[2].equals("")) {
                int age = Integer.parseInt(record[2]);
                customer.setAge(age);
            }
            for (int i = 4; i < record.length; i++){
                Contact contact = new Contact(record[i]);
                customer.addContact(contact);
            }
            allCustomers.add(customer);
        }
        return allCustomers;
    }
}
