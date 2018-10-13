package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.paging.Paging;
import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

public class BoardDaoList {


 
    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public BoardDaoList() {
        GetPropertyValue getPropertyValue = new GetPropertyValue();
        // DBConfiguration dbConfiguration = DBConfiguration.getInstance();
        try {
            getPropertyValue.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dbUrl = getPropertyValue.getDbUri();
        this.dbId = getPropertyValue.getDbUser();
        this.dbPassword = getPropertyValue.getDbPassword();
        System.out.println(dbUrl + "," + dbId + "," + dbPassword);
    }

    public List<BoardDO> getBoardListPerPage(Paging paging2) {


        List<BoardDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql ="select boardid,nickname,title,content,regdate from board order by boardid desc limit ? , ?";
            ps = conn.prepareStatement(sql);

            // 0~ 10까지만 가져오기.
            ps.setInt(1,paging2.getPerPage().getPageStart());
            ps.setInt(2,paging2.getPerPage().getPerPageNum());
            rs = ps.executeQuery();

            while(rs.next()) {
                BoardDO board = new BoardDO();
                board.setId(rs.getInt(1));
                board.setNickname(rs.getString(2));
                board.setTitle(rs.getString(3));
                board.setContent(rs.getString(4));

//                java.sql.Timestamp time = rs.getTimestamp(5);
                SimpleDateFormat ft =
                        new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
                Timestamp date2 = rs.getTimestamp(5);
                board.setDate(ft.format(date2));

//                Date dbDate = rs.getDate(5);
//                SimpleDateFormat ft =
//                        new SimpleDateFormat("yyyy.MM.dd 'at' hh:mm:ss a zzz");
//
//                java.util.Date date = new Date(dbDate.getTime());
//                LocalDateTime ldt = date.toInstant()
//                        .atZone(ZoneId.systemDefault())
//                        .toLocalDateTime();



//                SimpleDateFormat ft =
//                        new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//                System.out.println("Current Date: " + ft.format(time));


                list.add(board);
            }


        }catch(Exception ex) {
            ex.printStackTrace();
        }finally {
            DbUtil.close(conn,ps,rs);
        }

        return list;
    }

    public int getBoardListTotalCount() {
        List<BoardDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result =0;


        try {

            conn = DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql ="select count(*) from board";
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

    //added by siyoon
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


}
