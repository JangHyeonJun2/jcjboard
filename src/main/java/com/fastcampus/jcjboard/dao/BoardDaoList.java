package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.paging.Paging;
import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

                Date dbDate = rs.getDate(5);
                java.util.Date date = new Date(dbDate.getTime());
                LocalDateTime ldt = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDateTime();
                board.setDate(ldt);
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


}
