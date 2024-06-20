package DAO;

import VO.MemberVO;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

@Slf4j
public class MemberDAO {
    public void insert(MemberVO vo) throws Exception {
        String sql = "INSERT INTO member(memberId,memberPw,name,phone,email1,email2,gender,agree,createDate) VALUES(?,?,?,?,?,?,?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, vo.getMemberId());
        pstmt.setString(2, vo.getMemberPw());
        pstmt.setString(1, vo.getName());
        pstmt.setString(1, vo.getPhone());
        pstmt.setString(1, vo.getEmail1());
        pstmt.setString(1, vo.getEmail2());
        pstmt.setString(1, vo.getGender());
        pstmt.setInt(1, vo.getAgree());
        pstmt.setDate(1, (Date) vo.getCreateDate());

        pstmt.executeUpdate();
    }
}
