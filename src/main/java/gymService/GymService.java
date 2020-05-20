package gymService;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;

import gymService.exception.AlreadyExistsException;
import gymService.exception.ContactsClientException;
import gymService.exception.InvalidDataException;
import gymService.exception.NoDataFoundException;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

@WebService

@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface GymService {

    @WebMethod(operationName = "getAllMembersAndContacts")
    public List<MemberContact> getSoapAllMembers();


    @WebMethod(operationName = "getMembersAndContacts")
    public List<MemberContact> getSoapAMember(@WebParam(name="id") Integer id)
            throws NoDataFoundException;

    @WebMethod(operationName = "deleteMemberAndContacts")
    public String deleteSoapAMember(@WebParam(name="id") int id)
            throws NoDataFoundException, ContactsClientException;


    @WebMethod(operationName = "postMemberAndContacts")
    public String soapAddNewMember(@WebParam(name="id")int id,
                                   @WebParam(name="name") String name,
                                   @WebParam(name="surname") String surname,
                                   @WebParam(name="email") String email,
                                   @WebParam(name="number") String number,
                                   @WebParam(name="membershipStartDate")  Date membershipStart,
                                   @WebParam(name="membershipEndDate") Date membershipEnd,
                                   @WebParam(name="plan") String plan)
            throws InvalidDataException, ContactsClientException, AlreadyExistsException;

    @WebMethod(operationName = "putMemberAndContacts")
    public String soapPutMember(@WebParam(name="id") int id,
                                @WebParam(name="name") String name,
                                @WebParam(name="surname") String surname,
                                @WebParam(name="email") String email,
                                @WebParam(name="number") String number,
                                @WebParam(name="membershipStartDate") Date membershipStart,
                                @WebParam(name="membershipEndDate") Date membershipEnd,
                                @WebParam(name="plan") String plan)
            throws InvalidDataException, ContactsClientException, AlreadyExistsException;

}