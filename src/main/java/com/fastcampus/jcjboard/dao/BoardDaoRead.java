package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.BoardDO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.lang.String;
public class BoardDaoRead {


    private String dbUrl;
    private String dbId;
    private String dbPassword;



    public BoardDaoRead() {
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


    public BoardDO getBoardDO(int boardid) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        BoardDO board = new BoardDO();

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
                java.util.Date today = new Date();
                if (ft.format(today).equals(ft.format(date2))) {
                    ft = new SimpleDateFormat("a hh:mm");
                } else {
                    ft = new SimpleDateFormat("yy.MM.dd");
                }

                board.setDate(ft.format(date2));

                board.setViewCount(rs.getInt(6));
            }

            //조회수증가시키기
            String viewCountSql = "update board set view=view+1 where boardid=?";
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

}
