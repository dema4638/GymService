package gymService.exception;

import lombok.Getter;

import javax.ws.rs.core.Response;
import javax.xml.ws.WebFault;
import java.beans.XMLEncoder;
import java.io.ByteArrayOutputStream;

//@WebFault(name = "ContactsClientException", targetNamespace = "http://gymService/")
public class ContactsClientException extends Exception{
    @Getter
    private final Response response;
   // @Getter
   // private final ResponseFaultInfo faultInfo;

    public ContactsClientException(Response response, String message) {
        super(message);
        this.response = response;
//        this.faultInfo = new ResponseFaultInfo();
//        this.faultInfo.setFaultMessage(message);
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        XMLEncoder encoder = new XMLEncoder(baos);
//        encoder.writeObject(response.getEntity());
//        encoder.close();
//        String responseBody = new String(baos.toByteArray());
//
//        this.faultInfo.setResponse(response.getEntity() == null ? "No content" : response.getEntity());
    }

    public ContactsClientException(Response response) {
        super();
        this.response = response;
//        this.faultInfo = new ResponseFaultInfo();
//        this.faultInfo.setFaultMessage("");
//        this.faultInfo.setResponse(response.getEntity() == null ? "No content" : response.getEntity());
    }
}
