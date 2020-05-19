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
            if (member.getId() == id) {
                return member;
            }
        }
        return null;
    }

    public static void removeMember(int id) {
        for (Member member : memberList) {
            if (member.getId() == id) {
                memberList.remove(member);
                break;
            }
        }
    }

    public static void postNewMember(MemberContact memberContact) throws AlreadyExistsException, InvalidDataException {
        validateData(memberContact);

        if(memberList.stream().anyMatch(mem -> mem.getId() == memberContact.getMember().getId())) {
            throw new AlreadyExistsException("Member with id " +
                    memberContact.getMember().getId() + " already exists");
        }

        memberList.add(new Member(memberContact.getMember().getId(),
                memberContact.getMember().getMembershipStart(),
                memberContact.getMember().getMembershipEnd(),
                memberContact.getMember().getPlan()));
    }

    public static boolean memberExists(int id, MemberContact memberContact) {
        for (Member member : memberList) {
            if (id == member.getId()) {
                return true;
            }
        }
        return false;
    }

    public static void updateMember(MemberContact memberContact, int id) throws InvalidDataException {
        validateData(memberContact);
        for (Member member : memberList) {
            if (id == member.getId()) {
                member.setPlan(memberContact.getMember().getPlan());
                member.setMembershipEnd(memberContact.getMember().getMembershipEnd());
                member.setMembershipStart(memberContact.getMember().getMembershipStart());
                break;
            }
        }
    }

    public static void validateData(MemberContact memberContact) throws InvalidDataException {
        ArrayList<String> invalidFields = new ArrayList<String>();
        if(memberContact.getMember().getId() == null) {
            invalidFields.add("id");
        }
        if (memberContact.getMember().getMembershipEnd() == null) {
            invalidFields.add("membershipEnd");
        }
        if (memberContact.getMember().getMembershipStart() == null) {
            invalidFields.add("membershipStart");
        }
        if (memberContact.getMember().getPlan() == null) {
            invalidFields.add("plan");
        }
        if (!invalidFields.isEmpty()) {
            throw new InvalidDataException("Invalid fields: "+String.join(", ", invalidFields));
        }
    }
}


