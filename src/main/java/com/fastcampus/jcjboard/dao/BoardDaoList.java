package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardDaoList {

    private String dbUrl = "jdbc:mysql://localhost:3306/jcjboard?serverTimezone=UTC&useSSL=false";
    private String dbId = "root";
    private String dbPassword = "0653";

    public List<BoardDO> getBoardList() {
        List<BoardDO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            conn = DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql ="select boardid,nickname,title,content,regdate from board order by boardid desc";
            ps = conn.prepareStatement(sql);
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






}
