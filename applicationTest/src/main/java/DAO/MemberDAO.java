package DAO;


import VO.MemberVO;
import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;

import java.sql.*;

@Log4j2
public class MemberDAO {
    public void insert(MemberVO vo) throws Exception {
        String sql = "INSERT INTO member(member_id,member_pw,name,phone,email1,email2,gender,agree) VALUES(?,?,?,?,?,?,?,?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, vo.getMemberId());
        pstmt.setString(2, vo.getMemberPw());
        pstmt.setString(3, vo.getName());
        pstmt.setString(4, vo.getPhone());
        pstmt.setString(5, vo.getEmail1());
        pstmt.setString(6, vo.getEmail2());
        pstmt.setString(7, vo.getGender());
        pstmt.setInt(8, vo.getAgree());

        pstmt.executeUpdate();
    }
    public MemberVO getWithPassword(String memberId, String memberPw) throws Exception {
        String query = "SELECT member_id, member_pw FROM member WHERE member_id=? AND member_pw=?";
        MemberVO memberVO = new MemberVO();
        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, memberId);
        preparedStatement.setString(2, memberPw);
        @Cleanup ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        memberVO = MemberVO.builder()
                .memberId(resultSet.getString("member_id"))
                .memberPw(resultSet.getString("member_pw"))
                .build();
        return memberVO;
    }

}
