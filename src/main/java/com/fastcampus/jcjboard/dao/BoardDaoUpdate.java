package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardDaoUpdate {
    private String dbUrl;
    private String dbId;
    private String dbPassword;



    public BoardDaoUpdate() {
        GetPropertyValue getPropertyValue = new GetPropertyValue();

        try {
            getPropertyValue.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dbUrl = getPropertyValue.getDbUri();
        this.dbId = getPropertyValue.getDbUser();
        this.dbPassword = getPropertyValue.getDbPassword();

    }

    public String getDBpassword(BoardDO boardDO){
        String DBpassword = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql="SELECT password FROM board WHERE boardid=?";

        conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,boardDO.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                DBpassword = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps,rs);
        }

        return DBpassword;
    }

    public int updateBoardDO(BoardDO boardDO) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql2 = "update board set  nickname=?,title=?,content=? where boardid=? ";
            ps = conn.prepareStatement(sql2);

            ps.setString(1, boardDO.getNickname());
            ps.setString(2, boardDO.getTitle());
            ps.setString(3, boardDO.getContent());
            ps.setInt(4, boardDO.getId());
            count = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }

}
