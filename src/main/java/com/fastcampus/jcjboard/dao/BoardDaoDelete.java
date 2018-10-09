package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class BoardDaoDelete {
    private String dbUrl = "jdbc:mariadb://localhost:3306/Test_db";
    private String dbId = "siyoon";
    private String dbPassword = "1234";

    public BoardDaoDelete() {

    }

    public int deleteDB(String boardid) {
        int count =0;
        String sql = "DELETE FROM board WHERE boardid = ?";
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps);
        }
        return count;
    }
}
