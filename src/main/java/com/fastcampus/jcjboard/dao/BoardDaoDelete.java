package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;


public class BoardDaoDelete {

    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public BoardDaoDelete() {

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

    public int deleteDB(int boardid) {
        int count =0;
        String sql = "DELETE FROM board WHERE boardid = ?";
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,boardid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps);
        }
        return count;
    }

    public String getDbPassword(int boardid) {
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String DBpassword = null;

        String sql = "select password from board where boardid=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,boardid);

            rs = ps.executeQuery();
            if (rs.next()) {
                DBpassword = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps,rs);
        }

        return DBpassword;
    }
}
