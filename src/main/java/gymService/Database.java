package gymService;

import gymService.exception.AlreadyExistsException;
import gymService.exception.InvalidDataException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("ALL")
public class Database {
    @Getter
    @Setter
    private static ArrayList<Member> memberList = new ArrayList<Member>(List.of(
            new Member(12345, new Date(2020, 1, 2),
                    new Date(2020, 2, 2), "Standart"),
            new Member(74638, new Date(2019, 1, 2),
                    new Date(2020, 5, 2), "Premium"),
            new Member(87014, new Date(2020, 1, 6),
                    new Date(2020, 12, 20), "Standard"),
            new Member(11234, new Date(2020, 1, 29),
                    new Date(2020, 12, 20), "Standard")
    ));

    public static Member getMemberByID(int id) {
        for (Member member : memberList) {
            if (member.getID() == id) {
                return member;
            }
        }
        return null;
    }

    public static void removeMember(int id) {
        for (Member member : memberList) {
            if (member.getID() == id) {
                memberList.remove(member);
                break;
            }
        }
    }

    public static void postNewMember(MemberContact memberContact) throws AlreadyExistsException, InvalidDataException {
        validateData(memberContact);

        if(memberList.stream().anyMatch(mem -> mem.getID() == memberContact.getId())) {
            throw new AlreadyExistsException("Member with id " +
                    memberContact.getId() + " already exists");
        }

        memberList.add(new Member(memberContact.getId(),
                memberContact.getMembershipStart(),
                memberContact.getMembershipEnd(),
                memberContact.getPlan()));
    }

    public static boolean memberExists(int id, MemberContact memberContact) {
        for (Member member : memberList) {
            if (id == member.getID()) {
                return true;
            }
        }
        return false;
    }

    public static void updateMember(MemberContact memberContact, int id) throws InvalidDataException {
        validateData(memberContact);
        for (Member member : memberList) {
            if (id == member.getID()) {
                member.setPlan(memberContact.getPlan());
                member.setMembershipEnd(memberContact.getMembershipEnd());
                member.setMembershipStart(memberContact.getMembershipStart());
                break;
            }
        }
    }

    private static void validateData(MemberContact memberContact) throws InvalidDataException {
        ArrayList<String> invalidFields = new ArrayList<String>();
        if(memberContact.getId() == null) {
            invalidFields.add("id");
        }
        if (memberContact.getMembershipEnd() == null) {
            invalidFields.add("membershipEnd");
        }
        if (memberContact.getMembershipStart() == null) {
            invalidFields.add("membershipStart");
        }
        if (memberContact.getPlan() == null) {
            invalidFields.add("plan");
        }
        if (!invalidFields.isEmpty()) {
            throw new InvalidDataException("Invalid fields: "+String.join(", ", invalidFields));
        }
    }
}


