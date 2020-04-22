package gymService;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class MemberContact {

    private Member member;
    private Contact contact;

  /*  private Integer id;
    private String surname;
    private String name;
    private String number;
    private String email;
    private Date membershipStart;
    private Date membershipEnd;
    private String plan;*/

    public MemberContact(){

    }
    public MemberContact(Member member, Contact contact){
        this.member = member;
        this.contact = contact;
    }
}
