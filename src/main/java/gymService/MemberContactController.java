package gymService;

import gymService.exception.AlreadyExistsException;
import gymService.exception.ContactsClientException;
import gymService.exception.InvalidDataException;
import gymService.exception.NoDataFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/membersContacts")

public class MemberContactController {

    @Path("/")
    @GET
    @Produces("application/json")
    public Response getAllMemberWithContacts(){
        try {
            List<MemberContact> membersContacts = new ArrayList<>();
            ArrayList<Member> listOfMembers = Database.getMemberList();
            List<Contact> listOfContacts = new ContactsClient().getAllContacts();
            for (Member member : listOfMembers) {
                int i = 0;
                MemberContact memberContact = new MemberContact(member, null);
                while (i < listOfContacts.size()) {
                    if (member.getId().equals(listOfContacts.get(i).getId())) {
                         memberContact.setContact(listOfContacts.get(i));
                        break;
                    }
                    i++;
                }
                membersContacts.add(memberContact);
            }

            return Response.ok().entity(membersContacts).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({"application/json", "text/plain"})
    public Response getMemberWithContacts(@PathParam("id") int id) {
        try {
            Member mem = Database.getMemberByID(id);
            if (mem == null) {
                throw new NoDataFoundException("Member with id = " + id + " is not found");
            }
            Contact cont = new ContactsClient().getContactById(id);
            if (cont == null) {
                throw new NoDataFoundException("Contact with id = " + id + " is not found");
            }
            return Response.ok().entity(new MemberContact(mem, cont)).build();
        }  catch (NoDataFoundException ex) {
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("/{id}")
    @DELETE
    @Produces({"application/json", "text/plain"})
    public Response deleteMember(@PathParam("id") int id) {
        try {
            Database.removeMember(id);
            new ContactsClient().deleteContact(id);
            return Response.ok().build();
        } catch (ContactsClientException ex) {
            return ex.getResponse();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("/")
    @POST
    @Produces({"application/json", "text/plain"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handlePostRequest(MemberContact memberContact) {
        try {
            Contact contact;
            contact = getContact(memberContact);
            Database.postNewMember(memberContact);
            new ContactsClient().postContact(contact);
            return Response.ok().build();
        } catch (ContactsClientException ex) {
            return ex.getResponse();
        } catch (InvalidDataException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (AlreadyExistsException ex) {
            return Response.status(Response.Status.CONFLICT).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    @Path("/{id}")
    @PUT
    @Produces({"application/json", "text/plain"})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response handlePutRequest(MemberContact memberContact, @PathParam("id") int id) {
        try {
            boolean memberExisted = Database.memberExists(id, memberContact);
            memberContact.getContact().setId(id);
            memberContact.getMember().setId(id);
            if (memberExisted) {
                Database.updateMember(memberContact, id);
                Contact contact;
                contact = getContact(memberContact);
                new ContactsClient().putContact(contact, id);
            } else {
                addNewMember(memberContact);
            }
            return Response.ok().build();
        } catch (InvalidDataException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (ContactsClientException ex) {
            return ex.getResponse();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().build();
        }
    }

    public Contact getContact(MemberContact memberContact){
        return memberContact.getContact();
    }

    public void addNewMember(MemberContact memberContact) throws ContactsClientException, AlreadyExistsException, InvalidDataException {
        Contact contact;
        contact = getContact(memberContact);
        Database.postNewMember(memberContact);
        new ContactsClient().postContact(contact);
    }
}
