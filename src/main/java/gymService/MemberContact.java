package gymService;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class MemberContact {

    private Integer id;
    private String surname;
    private String name;
    private String number;
    private String email;
    private Date membershipStart;
    private Date membershipEnd;
    private String plan;

    public MemberContact(){

    }
    public MemberContact(Member member, Contact contact){
        this.id = member.getID();
        this.surname = contact.getSurname();
        this.name = contact.getName();
        this.number = contact.getNumber();
        this.email = contact.getEmail();
        this.membershipEnd = member.getMembershipEnd();
        this.membershipStart = member.getMembershipStart();
        this.plan = member.getPlan();
    }
}
