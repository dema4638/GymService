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


    @WebMethod(operationName = "getMemberAndContacts")
    public MemberContact getSoapAMember(@WebParam(name="id") @XmlElement(required = true) int id)
            throws NoDataFoundException;

    @WebMethod(operationName = "deleteMemberAndContacts")
    public String deleteSoapAMember(@WebParam(name="id") @XmlElement(required = true) int id)
            throws NoDataFoundException, ContactsClientException;


    @WebMethod(operationName = "postMemberAndContacts")
    public String soapAddNewMember(@WebParam(name="id") @XmlElement(required = true) int id,
                                   @WebParam(name="name") @XmlElement(required = true) String name,
                                   @WebParam(name="surname") @XmlElement(required = true) String surname,
                                   @WebParam(name="email") @XmlElement(required = true) String email,
                                   @WebParam(name="number") @XmlElement(required = true) String number,
                                   @WebParam(name="membershipStartDate") @XmlElement(required = true) Date membershipStart,
                                   @WebParam(name="membershipEndDate") @XmlElement(required = true) Date membershipEnd,
                                   @WebParam(name="plan") @XmlElement(required = true) String plan)
            throws InvalidDataException, ContactsClientException, AlreadyExistsException;

    @WebMethod(operationName = "putMemberAndContacts")
    public String soapPutMember(@WebParam(name="id") @XmlElement(required = true) int id,
                                @WebParam(name="name") @XmlElement(required = true) String name,
                                @WebParam(name="surname") @XmlElement(required = true) String surname,
                                @WebParam(name="email") @XmlElement(required = true) String email,
                                @WebParam(name="number") @XmlElement(required = true) String number,
                                @WebParam(name="membershipStartDate") @XmlElement(required = true) Date membershipStart,
                                @WebParam(name="membershipEndDate") @XmlElement(required = true) Date membershipEnd,
                                @WebParam(name="plan") @XmlElement(required = true) String plan)
            throws InvalidDataException, ContactsClientException, AlreadyExistsException;

}