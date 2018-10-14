package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.CommentVO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoRead {
    private String dbUrl;
    private String dbId;
    private String dbPassword;

    public CommentDaoRead() {
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
}
