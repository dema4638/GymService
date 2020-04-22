package gymService;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter @Setter
public class Member {
    //private Contact contact;

    private Integer id;
    private Date membershipStart;
    private Date membershipEnd;
    private String plan;

    public Member() {

    }

    public Member(int id, Date membershipStart, Date membershipEnd, String plan){
        this.id = id;
        this.membershipStart = membershipStart;
        this.membershipEnd = membershipEnd;
        this.plan = plan;
    }

}
