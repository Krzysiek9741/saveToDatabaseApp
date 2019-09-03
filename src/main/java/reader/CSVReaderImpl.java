package reader;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import model.Contact;
import model.Customer;
import model.Type;
import validator.TypeValidator;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderImpl implements Reader {
    private static CSVReaderImpl ourInstance = new CSVReaderImpl();

    // review dlaczego "singleton" - new nie boli
    public static CSVReaderImpl getInstance() {
        return ourInstance;
    }

    // review a przez konstruktor możemy zależności przekazać - np do Readerów. W SPosób zaproponowany blokuje taką możliwość i uniemożliwa przetestowanie jednostkowe (wiem, wiem - sa haki)
    private CSVReaderImpl() {
    }

    private List<String[]> getAllData(String filePath) {
        Path path = Paths.get(filePath);
        List<String[]> allRecords = new ArrayList<>();
        try (
                BufferedReader reader = Files.newBufferedReader(path);
                // review nie da się tego przetestować
                CSVReader csvReader = new CSVReaderBuilder(reader).build()
        ) {

            // review a co jeżeli mamy 40 mnl rekordów może się nie zmieścić do pamięci (jeden z warunków zadania - duuuużo danych)
            allRecords = csvReader.readAll();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allRecords;
    }

    @Override
    public List<Customer> getAllCustomers(String filePath) {

        // review jeżeli filePath jest NULL lub plik nie istnieje, to wykonamy mnóstwo kodu, zanim to zostanie sprawdzone. Zasada fail fast jest pominięta. Dodatkowo nie sprawdzamy kontraktu metody.
        List<String[]> allRecords = getAllData(filePath);
        List<Customer> allCustomers = new ArrayList<>();
        for (String[] record : allRecords) {
            String name = record[0];
            String surname = record[1];
            Customer customer = new Customer(name, surname);
            // review zawse porównuj stałą.equals(zmienna) - tutaj msz inaczej.
            if (!record[2].equals("")) {
                // review a co jeżeli wyjątek będzie? Błąd w ostatniej linni z 40 mln? nic się nie powiedzie. :/
                int age = Integer.parseInt(record[2]);
                customer.setAge(age);
            }
            for (int i = 4; i < record.length; i++) {
                // review baaardzo słabe wywołanie metody replaceAll(). Wejdź do środka a zobaczysz, że jest tam przy każdym wywołaniu jej wykonywana kompilacja wzorca
                // review a kompilacja wzorca jest czasocłonna - należałoby tego uniknąć (wyciągnąć kompilację na zewnątrz pętli przynajmniej. Albo do pola klasy)
                String contactString = record[i].replaceAll("\\s+", "");
                String contactType = TypeValidator.checkType(contactString);
                Contact contact = new Contact(contactType, contactString);
                customer.addContact(contact);
            }
            allCustomers.add(customer);
        }
        return allCustomers;

    }
}
