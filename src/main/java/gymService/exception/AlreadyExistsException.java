package gymService.exception;

public class AlreadyExistsException extends Exception {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException() {
        super();
    }
}
