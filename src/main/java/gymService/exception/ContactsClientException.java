package gymService.exception;

import lombok.Getter;

import javax.ws.rs.core.Response;

public class ContactsClientException extends Exception{
    @Getter
    Response response;

    public ContactsClientException(Response response, String message) {
        super(message);
        this.response = response;
    }

    public ContactsClientException(Response response) {
        super();
        this.response = response;
    }
}
