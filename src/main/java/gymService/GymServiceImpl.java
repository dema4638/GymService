package gymService;

import com.sun.tools.jconsole.JConsoleContext;
import com.sun.xml.ws.encoding.soap.SOAPConstants;
import com.sun.xml.ws.wsdl.writer.document.Fault;
import com.sun.xml.ws.wsdl.writer.document.soap.SOAPFault;
import gymService.exception.AlreadyExistsException;
import gymService.exception.ContactsClientException;
import gymService.exception.InvalidDataException;
import gymService.exception.NoDataFoundException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.List;


@WebService(endpointInterface = "gymService.GymService")
public class GymServiceImpl implements GymService{

    private ServiceLogic serviceLogic = new ServiceLogic();
    private MemberContactController memberContactController = new MemberContactController();

    @Override
    public List<MemberContact> getSoapAllMembers() {
        return serviceLogic.getAllMembersAndContacts();
    }

    @Override
    public MemberContact getSoapAMember(@WebParam(name="id") int id) throws NoDataFoundException {
        try {
            return serviceLogic.getMemberWithContacts(id);
        } catch (NoDataFoundException ex) {
            throw ex;
        }

        }
    @Override
    public String deleteSoapAMember(@WebParam(name="id") int id) throws ContactsClientException {

            serviceLogic.deleteMemberAndContacts(id);
            return "Member with id " + id + " and their contacts deleted.";

        //catch (ContactsClientException ex) {
          //  throw ex;
        //}
        }

    @Override
    public String soapAddNewMember(@WebParam(name="id") int id, @WebParam(name="name") String name,
                                   @WebParam(name="surname") String surname, @WebParam(name="email") String email,
                                   @WebParam(name="number") String number, @WebParam(name="membershipStartDate") Date membershipStart,
                                   @WebParam(name="membershipEndDate") Date membershipEnd,@WebParam(name="plan") String plan)
            throws InvalidDataException, ContactsClientException, AlreadyExistsException {

        MemberContact memberContact = new MemberContact();
        setMemberContact(id, number, name, surname, email, membershipStart, membershipEnd, plan, memberContact);
        serviceLogic.postNewMemberAndContacts(memberContact);
        return "Member with id: "+id+" and their contacts added";
    }

    @Override
    public String soapPutMember(@WebParam(name="id") int id, @WebParam(name="name") String name,
                                @WebParam(name="surname") String surname, @WebParam(name="email") String email,
                                @WebParam(name="number") String number, @WebParam(name="membershipStartDate") Date membershipStart,
                                @WebParam(name="membershipEndDate") Date membershipEnd,@WebParam(name="plan") String plan)
            throws InvalidDataException, ContactsClientException, AlreadyExistsException {
        MemberContact memberContact = new MemberContact();
        setMemberContact(id, number, name, surname, email, membershipStart, membershipEnd, plan, memberContact);
        boolean memberExisted = serviceLogic.putMemberAndContacts(id, memberContact);
        if (memberExisted) {
            return "Member with id: "+id+" and their contacts updated";
        }
        else{
            return "Member with id: "+id+" and their contacts added";
        }
    }

    public void setMemberContact(int id, String number, String name, String surname, String email,
                           Date membershipStart, Date membershipEnd, String plan, MemberContact memberContact){
        Contact contact = new Contact();
        contact.setId(id);
        contact.setNumber(number);
        contact.setName(name);
        contact.setSurname(surname);
        contact.setEmail(email);
        memberContact.setContact(contact);
        Member member = new Member();
        member.setMembershipStart(membershipStart);
        member.setMembershipEnd(membershipEnd);
        member.setPlan(plan);
        member.setId(id);
        memberContact.setMember(member);
    }

}

