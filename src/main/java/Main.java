import reader.CSVReaderImpl;
import reader.Reader;
import service.DBWriter;

public class Main {

    public static void main(String[] args) {

        DBWriter.getInstance().saveToDB();

    }
}
