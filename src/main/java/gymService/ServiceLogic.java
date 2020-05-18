package gymService;

import gymService.exception.AlreadyExistsException;
import gymService.exception.ContactsClientException;
import gymService.exception.InvalidDataException;
import gymService.exception.NoDataFoundException;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class ServiceLogic {

    public List<MemberContact> getAllMembersAndContacts() {
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

        return membersContacts;

    }

    public MemberContact getMemberWithContacts(int id) throws NoDataFoundException {
        Member mem = Database.getMemberByID(id);
        if (mem == null) {
            throw new NoDataFoundException("Member with id = " + id + " is not found");
        }
        Contact cont = new ContactsClient().getContactById(id);
        if (cont == null) {
            throw new NoDataFoundException("Contact with id = " + id + " is not found");
        }
        return new MemberContact(mem, cont);
    }

    public void deleteMemberAndContacts(int id) throws ContactsClientException {
        Database.removeMember(id);
        new ContactsClient().deleteContact(id);
    }

    public void postNewMemberAndContacts(MemberContact memberContact) throws AlreadyExistsException,
            InvalidDataException, ContactsClientException {
        Contact contact;
        contact = memberContact.getContact();
        Database.postNewMember(memberContact);
        new ContactsClient().postContact(contact);
    }

    public boolean putMemberAndContacts(int id, MemberContact memberContact) throws InvalidDataException,
            ContactsClientException, AlreadyExistsException {
        boolean memberExisted = Database.memberExists(id, memberContact);
        memberContact.getContact().setId(id);
        memberContact.getMember().setId(id);
        if (memberExisted) {
            Database.updateMember(memberContact, id);
            Contact contact;
            contact = memberContact.getContact();
            new ContactsClient().putContact(contact, id);
            return true;
        } else {
            postNewMemberAndContacts(memberContact);
            return false;
        }
    }
}
