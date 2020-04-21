package gymService.exception;

public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }

    public InvalidDataException() {
        super();
    }
}
