package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class BoardDaoWrite {
    private String dbUrl = "jdbc:mariadb://localhost:3306/Test_db";
    private String dbId = "siyoon";
    private String dbPassword = "1234";

    public BoardDaoWrite() {

    }

    public int addBoardDO(BoardDO boardDO) {
        int count =0;

        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        String sql = "insert into board(nickname,title,content,regdate,password) values (?,?,?,?,?)";
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,boardDO.getNickname()); //바인딩
            ps.setString(2,boardDO.getTitle());
            ps.setString(3,boardDO.getContent());

            LocalDate ld = LocalDate.now();
            Date date = Date.valueOf(ld);
            ps.setDate(4,date);

            ps.setString(5,boardDO.getPassword());

            count = ps.executeUpdate(); //쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps);
        }

        return count;
    }
}
