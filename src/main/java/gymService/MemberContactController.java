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

    private ServiceLogic serviceLogic = new ServiceLogic();

    @Path("/")
    @GET
    @Produces("application/json")
    public Response getRestResponseForAllMembers() {
        try {
            List<MemberContact> membersContacts;
            membersContacts = serviceLogic.getAllMembersAndContacts();
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
            MemberContact memberContact = serviceLogic.getMemberWithContacts(id);
            return Response.ok().entity(memberContact).build();
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
           // Database.removeMember(id);
            //new ContactsClient().deleteContact(id);
            serviceLogic.deleteMemberAndContacts(id);
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
            serviceLogic.postNewMemberAndContacts(memberContact);
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
            serviceLogic.putMemberAndContacts(id, memberContact);
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
}
