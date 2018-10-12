package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentDaoDelete {
    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public CommentDaoDelete() {
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



    public int deleteComment(int commentid) {
        int count =0;
        String sql = "DELETE FROM comment WHERE commentid = ?";
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commentid);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps);
        }
        return count;
    }

    public String getCommentPassword(int commentid) {
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;
        ResultSet rs = null;
        String DBpassword = null;

        String sql = "select password from comment where commentid=?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commentid);

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
