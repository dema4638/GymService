package gymService;

import gymService.exception.ContactsClientException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ContactsClient {

    private final Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target("http://contacts-service:5000/");
    private List<Contact> listOfContacts;

    public ContactsClient(){
        WebTarget memberWebTarget = webTarget.path("contacts");
        Invocation.Builder invocationBuilder = memberWebTarget.request(MediaType.APPLICATION_JSON);
        listOfContacts = invocationBuilder.get(new GenericType<List<Contact>>() {});
    }

    public List<Contact> getAllContacts() {
        WebTarget memberWebTarget = webTarget.path("contacts");
        Invocation.Builder invocationBuilder = memberWebTarget.request(MediaType.APPLICATION_JSON);
        listOfContacts = invocationBuilder.get(new GenericType<List<Contact>>() {});
        return listOfContacts;
    }

    public Contact getContactById(int id){
        for (Contact contact : listOfContacts){
            if (contact.getId() == id){
                return contact;
            }
        }
        return null;
    }

    public void deleteContact(int id) throws ContactsClientException {
        WebTarget memberWebTarget = webTarget.path("contacts/"+id);
        Invocation.Builder invocationBuilder = memberWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.delete(Response.class);
        if (!(response.getStatus() >= 200 && response.getStatus() < 400)) {
            throw new ContactsClientException(response);
        }
    }

    public void postContact(Contact contact) throws ContactsClientException {
        System.out.println(contact.getEmail());
        WebTarget memberWebTarget = webTarget.path("contacts");
        Invocation.Builder invocationBuilder = memberWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(contact, MediaType.APPLICATION_JSON));
        if (!(response.getStatus() >= 200 && response.getStatus() < 400)) {
            throw new ContactsClientException(response);
        }
    }

    public void putContact(Contact contact, int id) throws ContactsClientException {
        WebTarget memberWebTarget = webTarget.path("contacts/"+ id);
        Invocation.Builder invocationBuilder = memberWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.put(Entity.entity(contact, MediaType.APPLICATION_JSON));
        if (!(response.getStatus() >= 200 && response.getStatus() < 400)) {
            throw new ContactsClientException(response);
        }
    }
}
