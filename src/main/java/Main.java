// review brak jakiegokolwiek pakietu
// review nieużywane importy
import reader.CSVReaderImpl;
import reader.Reader;
import service.DBWriter;

public class Main {

    public static void main(String[] args) {

        // review nie jest aż tak straszne, ale dlaczego nie utworzyć obkietu za pomocą new? metoda statyczna, singleton nie jest tutaj najlepszy
        // review dodatkowo - nie ma jak wstrzyknać zależności (i nie chodzi mi to o Spring)
        DBWriter.getInstance().saveToDB();

    }
}
