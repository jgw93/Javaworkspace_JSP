package DAO;

import DTO.NoticeDTO;
import VO.NoticeVO;
import lombok.Cleanup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NoticeDAO {

    public List<NoticeVO> selectAll() throws Exception{
        String sql = "SELECT * FROM notice";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        @Cleanup ResultSet resultSet = pstmt.executeQuery();
        List<NoticeVO> list = new ArrayList<NoticeVO>();
        while (resultSet.next()) {
            NoticeVO vo = NoticeVO.builder()
                    .tno(resultSet.getLong("tno"))
                    .title(resultSet.getString("title"))
                    .content(resultSet.getString("content"))
                    .count(resultSet.getLong("count"))
                    .createDate(resultSet.getDate("create_date").toLocalDate())
                    .build();
            list.add(vo);
        }
        return list;
    }

    public NoticeVO selectOne(Long tno) throws Exception {
        String sql = "SELECT * FROM notice WHERE tno = ?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1,tno);
        @Cleanup ResultSet resultSet = pstmt.executeQuery();
        resultSet.next();
        NoticeVO vo = NoticeVO.builder()
                .tno(resultSet.getLong("tno"))
                .title(resultSet.getString("title"))
                .build();
        return vo;
    }

    public void deleteOne(Long tno) throws Exception {
        String sql = "DELETE FROM notice WHERE tno=?";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, tno);
        pstmt.executeUpdate();
    }

    public void insertOne(NoticeDTO dto) throws Exception {
        String sql = "INSERT INTO notice(title, content) VALUES (?, ?)";
        @Cleanup Connection connection = ConnectionUtil.INSTANCE.getConnection();
        @Cleanup PreparedStatement pstmt = connection.prepareStatement(sql);

        pstmt.setString(1, dto.getTitle());
        pstmt.setString(2, dto.getContent());
        pstmt.executeUpdate();
    }
}
