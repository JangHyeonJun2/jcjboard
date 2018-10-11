package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class BoardDaoWrite {


    /*
    private String dbUrl = "jdbc:mysql://localhost:3306/jcjboard?serverTimezone=UTC&useSSL=false";
    private String dbId = "root";
    private String dbPassword = "0653";
    */
    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public BoardDaoWrite() {
        GetPropertyValue getPropertyValue = new GetPropertyValue();
        //DBConfiguration dbConfiguration = DBConfiguration.getInstance();
        try {
            getPropertyValue.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dbUrl = getPropertyValue.getDbUri();
        this.dbId = getPropertyValue.getDbUser();
        this.dbPassword = getPropertyValue.getDbPassword();

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
