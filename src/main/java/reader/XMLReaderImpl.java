package reader;

import model.Contact;
import model.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLReaderImpl {
    private static XMLReaderImpl ourInstance = new XMLReaderImpl();

    public static XMLReaderImpl getInstance() {
        return ourInstance;
    }

    private XMLReaderImpl() {
    }

    public List<Customer> getAllCustomers(String filePath){

        List<Customer> customers = new ArrayList<>();

    try {
        File fXmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);

        doc.getDocumentElement().normalize();


        NodeList nList = doc.getElementsByTagName("person");


        for (int i = 0; i < nList.getLength(); i++) {

            Node cust = nList.item(i);


            if (cust.getNodeType() == Node.ELEMENT_NODE) {

                Element custElement = (Element) cust;

                String name = custElement.getElementsByTagName("name").item(0).getTextContent();
                String surname = custElement.getElementsByTagName("surname").item(0).getTextContent();
                Customer customer = new Customer(name, surname);

                if (custElement.getElementsByTagName("age").item(0) != null) {
                    int age = Integer.parseInt(custElement.getElementsByTagName("age").item(0).getTextContent());
                    customer.setAge(age);
                }

                NodeList contactsNode = custElement.getElementsByTagName("contacts");
                NodeList contacts = contactsNode.item(0).getChildNodes();
                for (int j = 0; j < contacts.getLength();j++){
                    if (!contacts.item(j).getTextContent().contains("\n")) {
                        customer.addContact(new Contact(contacts.item(j).getTextContent()));
                    }
                }

                customers.add(customer);

            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return customers;

}

}

