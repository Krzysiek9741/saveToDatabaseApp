package exception;

// review dlaczego Exception a nie RuntimeException?
public class UnsupportedFileFormatException extends Exception {

    public UnsupportedFileFormatException(String message) {
        super(message);
    }
}
