package org.zerock.w2.DAO;

import lombok.Cleanup;
import org.checkerframework.checker.units.qual.C;
import org.zerock.w2.domain.MemberVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MemberDAO {
    public MemberVO getWithPassword(String mid, String mpw) throws Exception {
        String query = "select mid, mpw, mname from tbl_member where mid = ? and mpw = ?";

        MemberVO memberVO = null;

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);

        pstmt.setString(1, mid);
        pstmt.setString(2, mpw);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        memberVO = MemberVO.builder()
                .mid(rs.getString(1))
                .mpw(rs.getString(2))
                .mname(rs.getString(3))
                .build();

        return memberVO;
    }

    public void updateUuid(String mid, String uuid) throws Exception {
        String sql = "update tbl_member set uuid = ? where mid = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(sql);

        pstmt.setString(1, uuid);
        pstmt.setString(2, mid);
        pstmt.executeUpdate();
    }

    public MemberVO selectUUID(String uuid) throws Exception {
        String query = "select mid, mpw, mname, uuid from tbl_member where uuid = ?";

        @Cleanup Connection conn = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, uuid);

        @Cleanup ResultSet rs = pstmt.executeQuery();

        rs.next();

        MemberVO memberVO = MemberVO.builder()
                .mid(rs.getString(1))
                .mpw(rs.getString(2))
                .mname(rs.getString(3))
                .uuid(rs.getString(4))
                .build();

        return memberVO;
    }
}
