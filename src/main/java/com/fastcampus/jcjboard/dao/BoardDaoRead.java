package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
public class BoardDaoRead {


    private String dbUrl;
    private String dbId;
    private String dbPassword;



    public BoardDaoRead() {
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


    public List<BoardDO> getBoardList() {
        List<BoardDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            String sql = "select boardid,nickname,title,content from board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                BoardDO board = new BoardDO();
                board.setId(rs.getInt(1));
                board.setNickname(rs.getString(2));
                board.setTitle(rs.getString(3));
                board.setContent(rs.getString(4));
                list.add(board);
                //System.out.println(board.getId() + board.getContent());
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        return list;
    }

 //두번쨰
    public List<BoardDO> getBoardList(String sql) {
        List<BoardDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DbUtil.connect(dbUrl, dbId, dbPassword);
            //String sql ="select boardid,nickname,title,content from board";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                BoardDO board = new BoardDO();
                board.setId(rs.getInt(1));
                board.setNickname(rs.getString(2));
                board.setTitle(rs.getString(3));
                board.setContent(rs.getString(4));
                list.add(board);
                //System.out.println(board.getId() + board.getContent());
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            DbUtil.close(conn, ps, rs);
        }

        return list;
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
    public boolean getpassword(BoardDO boardDO){
        Connection conn = null;
        PreparedStatement ps = null;
        String sqlpassword="";
        boolean value = false;
        try{
            conn = DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql3 = "select password from board where boardid="+boardDO.getId();
            ps = conn.prepareStatement(sql3);
            ResultSet rs = ps.executeQuery(sql3);
            if(rs.next()){
                sqlpassword = rs.getString(1);
            }
            if(sqlpassword.equals(boardDO.getPassword()))
                value= true;
            else
                value= false;
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

}
