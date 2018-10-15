package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.CommentVO;
import com.fastcampus.jcjboard.util.DbConfProperty;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public CommentDao() {
        DbConfProperty dbConfProperty = new DbConfProperty();

        try {
            dbConfProperty.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dbUrl = dbConfProperty.getDbUrl();
        this.dbId = dbConfProperty.getDbUser();
        this.dbPassword = dbConfProperty.getDbPassword();
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

    /*commentDaoRead관련*/
    public List<CommentVO> getCommentList(int id){
        List<CommentVO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn =DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql = "select commentid,nickname,content,regdate,password,boardid from comment where boardid=?";

            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while(rs.next()){
                CommentVO commentVO = new CommentVO();

                commentVO.setCommentid(rs.getInt(1));
                commentVO.setNickname(rs.getString(2));
                commentVO.setContent(rs.getString(3));
                SimpleDateFormat ft =
                        new SimpleDateFormat("yyyy.MM.dd");
                Timestamp date2 = rs.getTimestamp(4);
                // 오늘 날짜면은 시간만 표시, 오늘 이전이면 날짜만 표
                java.util.Date today = new java.util.Date();
                if (ft.format(today).equals(ft.format(date2))) {
                    ft = new SimpleDateFormat("a hh:mm");
                } else {
                    ft = new SimpleDateFormat("yy.MM.dd");
                }

                commentVO.setDate(ft.format(date2));
                commentVO.setPassword(rs.getString(5));
                commentVO.setBoardid(rs.getInt(6));
                list.add(commentVO);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DbUtil.close(conn,ps,rs);
        }
        return list;
    }

    /*commentDaoUpdate 관련*/
    public CommentVO getComment(int commentid) {
        CommentVO comment = new CommentVO();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "select commentid,nickname,content,regdate,boardid from comment where commentid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,commentid);
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
    }

    public int updateComment(CommentVO commentVO) {
        int count=0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "update comment set nickname=?, content=? , regdate=now() where commentid=? ";
            ps = conn.prepareStatement(sql);

            ps.setString(1, commentVO.getNickname());
            ps.setString(2, commentVO.getContent());
            ps.setInt(3, commentVO.getCommentid());
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }
    /*commentDaoWrite 관련*/
    public int addComment(CommentVO commentVO) {
        int count=0;
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;
        String sql ="INSERT INTO comment(nickname,content,regdate,password,boardid) values (?,?,now(),?,?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,commentVO.getNickname());
            ps.setString(2,commentVO.getContent());
            ps.setString(3,commentVO.getPassword());
            ps.setInt(4,commentVO.getBoardid());


            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }

}
