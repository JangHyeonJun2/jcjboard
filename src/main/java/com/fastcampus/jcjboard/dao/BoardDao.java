package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.paging.Paging;
import com.fastcampus.jcjboard.servlet.ArticleVO;
import com.fastcampus.jcjboard.util.DbConfProperty;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalTime.now;

public class BoardDao {
    private String dbUrl;
    private String dbId;
    private String dbPassword;


    public BoardDao() {
        DbConfProperty dbConfProperty = DbConfProperty.getInstance();

        this.dbUrl = dbConfProperty.getDbUrl();
        this.dbId = dbConfProperty.getDbUser();
        this.dbPassword = dbConfProperty.getDbPassword();
    }
    /*Read 관련*/
    public ArticleVO getArticleVO(int boardid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArticleVO board = new ArticleVO();

        try {
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "SELECT boardid, nickname, title, content, regdate, view FROM board WHERE boardid=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,boardid);
            rs = ps.executeQuery();

            while (rs.next()) {
                board.setId(rs.getInt(1));
                board.setNickname(rs.getString(2));
                board.setTitle(rs.getString(3));
                board.setContent(rs.getString(4));

                SimpleDateFormat ft =
                        new SimpleDateFormat("yyyy.MM.dd");
                Timestamp date2 = rs.getTimestamp(5);

                // 오늘 날짜면은 시간만 표시, 오늘 이전이면 날짜만 표시
                Date today = new Date();
                if (ft.format(today).equals(ft.format(date2))) {
                    ft = new SimpleDateFormat("a hh:mm");
                } else {
                    ft = new SimpleDateFormat("yy.MM.dd");
                }

                board.setDate(ft.format(date2));
                board.setViewCount(rs.getInt(6));
            }

            //조회수증가시키기
            String viewCountSql = "UPDATE board SET view=view+1 WHERE boardid=?";
            ps = conn.prepareStatement(viewCountSql);
            ps.setInt(1,board.getId());
            ps.executeUpdate();


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        return board;
    }

    /*delete 관련*/
    public int deleteArticleVO(int boardid, String password) {
        int count =0;
        String sql = "DELETE FROM board WHERE boardid = ? AND password = ?";
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,boardid);
            ps.setString(2,password);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps);
        }
        return count;
    }

    /*List 관련*/
    public List<ArticleVO> getArticleListPerPage(Paging paging2) {


        List<ArticleVO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql ="SELECT boardid,nickname,title,content,regdate,view FROM board ORDER BY boardid DESC limit ? , ?";
            ps = conn.prepareStatement(sql);

            // 0~ 10까지만 가져오기.
            ps.setInt(1,paging2.getPerPage().getPageStart());
            ps.setInt(2,paging2.getPerPage().getPerPageNum());
            rs = ps.executeQuery();

            while(rs.next()) {
                ArticleVO board = new ArticleVO();
                board.setId(rs.getInt(1));
                board.setNickname(rs.getString(2));
                board.setTitle(rs.getString(3));
                board.setContent(rs.getString(4));

//                java.sql.Timestamp time = rs.getTimestamp(5);

                SimpleDateFormat ft =
                        new SimpleDateFormat("yyyy.MM.dd");
                Timestamp date2 = rs.getTimestamp(5);

                // 오늘 날짜면은 시간만 표시, 오늘 이전이면 날짜만 표
                Date today = new Date();
                if (ft.format(today).equals(ft.format(date2))) {
                    ft = new SimpleDateFormat("a hh:mm");
                } else {
                    ft = new SimpleDateFormat("yy.MM.dd");
                }
                //
                board.setDate(ft.format(date2));

                //조회수 받아오기 add by siyoon
                board.setViewCount(rs.getInt(6));
                list.add(board);
            }


        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn,ps,rs);
        }

        return list;
    }

    public int getArticleListTotalCount() {
        List<ArticleVO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result =0;


        try {

            conn = DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql ="SELECT count(*) FROM board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                result = rs.getInt(1);
            }

        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn,ps,rs);
        }

        return result;
    }

    public int getCommentCount(int id) {
        int comment =0;
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sql ="SELECT Count(*) FROM comment WHERE boardid=?";

        conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()) {
                comment=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps,rs);
        }
        return comment;
    }

    /*Update 관련*/
    public int updateArticleVO(ArticleVO articleVO,String password) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql2 = "UPDATE board SET nickname=?,title=?,content=? WHERE boardid=? AND password = ?";
            ps = conn.prepareStatement(sql2);

            ps.setString(1, articleVO.getNickname());
            ps.setString(2, articleVO.getTitle());
            ps.setString(3, articleVO.getContent());
            ps.setInt(4, articleVO.getId());
            ps.setString(5, password);
            count = ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }

    /*write 관련*/
    public int addArticleVO(ArticleVO articleVO) {
        int count =0;

        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        String sql = "INSERT INTO board(nickname,title,content,regdate,password) VALUES (?,?,?,now(),?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, articleVO.getNickname()); //바인딩
            ps.setString(2, articleVO.getTitle());
            ps.setString(3, articleVO.getContent());

            ps.setString(4, articleVO.getPassword());

            count = ps.executeUpdate(); //쿼리 실행
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn,ps);
        }

        return count;
    }

}
