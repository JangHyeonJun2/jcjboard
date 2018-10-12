package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.CommentVO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CommentDaoUpdate {

    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public CommentDaoUpdate() {
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


    public CommentVO getCommentOne(int commentid) {
        CommentVO comment = new CommentVO();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "select commentid,nickname,content,regdate,boardid from comment where commentid=" + commentid;
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {

                comment.setCommentid(rs.getInt(1));
                comment.setNickname(rs.getString(2));
                comment.setContent(rs.getString(3));
                SimpleDateFormat ft =
                        new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                Timestamp date2 = rs.getTimestamp(4);
                comment.setDate(ft.format(date2));
                comment.setBoardid(rs.getInt(5));
            }



        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn,ps,rs);
        }

        return comment;

    } // getCommentOne



    public int updateComment(CommentVO commentVO) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "update comment set content=? , regdate=now() where commentid=? ";
            ps = conn.prepareStatement(sql);

            ps.setString(1, commentVO.getContent());
            ps.setInt(2, commentVO.getCommentid());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }



}
