package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.CommentVO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommentDaoWrite {
    private String dbUrl;
    private String dbId;
    private String dbPassword;
    public CommentDaoWrite() {
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
    public int addComment(CommentVO commentVO) {
        int count=0;
        Connection conn = DbUtil.connect(dbUrl, dbId, dbPassword);
        PreparedStatement ps = null;
        String sql ="INSERT INTO comment(nickname,content,regdate,password,boardid) values (?,?,?,?,?);";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1,commentVO.getNickname());
            ps.setString(2,commentVO.getContent());
            LocalDate ld = LocalDate.now();
            Date date = Date.valueOf(ld);
            ps.setDate(3,date);
            ps.setString(4,commentVO.getPassword());
            ps.setInt(5,commentVO.getBoardid());


            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DbUtil.close(conn, ps);
        }
        return count;
    }
    public List<CommentVO> showcommentList(){
        List<CommentVO> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn =DbUtil.connect(dbUrl,dbId,dbPassword);
            String sql = "select commentid,nickname,content,regdate,password,boardid from comment";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                CommentVO commentVO = new CommentVO();
                commentVO.setCommentid(rs.getInt(1));
                commentVO.setNickname(rs.getString(2));
                commentVO.setContent(rs.getString(3));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
