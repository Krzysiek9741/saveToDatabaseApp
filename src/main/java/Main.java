import database.Dao;
import model.Contact;
import model.Customer;
import model.Type;
import reader.CSVReaderImpl;
import reader.XMLReaderImpl;
import service.DBWriter;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        String path = "C:\\dane\\dane-osoby.csv";
        String path2 = "C:\\dane\\dane-osoby.xml";

        /*List<Customer> customers = CSVReaderImpl.getInstance().getAllCustomers(path);
        List<Customer> customers2 = XMLReaderImpl.getInstance().getAllCustomers(path2);

        for (Customer customer:customers2) {
            System.out.println(customer.getName());
            for (Contact contact:customer.getContacts()){
                System.out.println(contact);
            }
            System.out.println("------------------------------------------");
            System.out.println(customer.getContacts().size());
        }*/

        /*Customer customer1 = new Customer("Jan", "Nowak", 33);
        Customer customer2 = new Customer("Adam", "Bereś", 28);
        customer1.addContact(new Contact("email", "jan123@wp.pl"));
        customer1.addContact(new Contact("phone", "65445685"));
        customer1.addContact(new Contact("abc", "321"));
        customer2.addContact(new Contact("email", "adasko@onet.pl"));
        customer2.addContact(new Contact("jabber", "jab:123123"));
        customer2.addContact(new Contact("phone", "888456123"));
        Dao.getInstance().insertNewRecord(customer1);
        Dao.getInstance().insertNewRecord(customer2);*/

        DBWriter.getInstance().saveToDB();


    }
}
