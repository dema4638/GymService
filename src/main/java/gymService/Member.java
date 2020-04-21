package gymService;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class Member {
    //private Contact contact;
    private Integer ID;
    private Date membershipStart;
    private Date membershipEnd;
    private String plan;

    public Member(int ID, Date membershipStart, Date membershipEnd, String plan){
        this.ID = ID;
        this.membershipStart = membershipStart;
        this.membershipEnd = membershipEnd;
        this.plan = plan;
    }

}
