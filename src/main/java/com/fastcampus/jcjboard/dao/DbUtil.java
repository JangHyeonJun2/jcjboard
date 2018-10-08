package com.fastcampus.jcjboard.dao;

import java.sql.*;

public class DbUtil {

    public static Connection connect(String dbUrl, String dbId, String dbPassword)
        throws RuntimeException {

        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbUrl,dbId,dbPassword);

        }catch (Exception ex) {
            throw new RuntimeException();
        }
        return conn;
    } // connect


    // insert, update, delete 사용시에 이 close() 사용.
    // select 만 ResultSet 이 필요하기 때문에. 다른 close() 사용.
    public static void close(Connection conn, PreparedStatement ps) {
        if(ps != null) {
            try {
                ps.close();
            }catch(SQLException e) {
            }
        }

        if(conn != null) {
            try {
                conn.close();
            }catch (SQLException e) {

            }
        }

    } // close


    public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
        if(rs != null) {
            try {
                rs.close();
            }catch(SQLException e) {}
        }
        close(conn,ps);
    }
}
