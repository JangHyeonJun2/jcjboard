package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.CommentVO;
import com.fastcampus.jcjboard.servlet.GetPropertyValue;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
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
