package VO;

import lombok.*;

import java.util.Date;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private String memberId;
    private String memberPw;
    private String name;
    private String phone;
    private String email1;
    private String email2;
    private String gender;
    private int agree;
    private Date createDate;

}
