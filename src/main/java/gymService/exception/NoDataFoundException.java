package gymService.exception;

import javax.xml.ws.WebFault;

@WebFault()
public class NoDataFoundException extends Exception {
    public NoDataFoundException(String message) {
        super(message);
    }

    public NoDataFoundException() {
        super();
    }
}
