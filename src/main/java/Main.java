import model.Contact;
import model.Customer;
import reader.CSVReaderImpl;
import reader.XMLReaderImpl;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\dane\\dane-osoby.csv";
        String path2 = "C:\\dane\\dane-osoby.xml";

        List<Customer> customers = CSVReaderImpl.getInstance().getAllCustomers(path);
        List<Customer> customers2 = XMLReaderImpl.getInstance().getAllCustomers(path2);

        for (Customer customer:customers2) {
            System.out.println(customer.getName());
            for (Contact contact:customer.getContacts()){
                System.out.println(contact);
            }
            System.out.println("------------------------------------------");
            System.out.println(customer.getContacts().size());
        }


    }
}
