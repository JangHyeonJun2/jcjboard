package com.fastcampus.jcjboard.dao;

import com.fastcampus.jcjboard.servlet.GetPropertyValue;

import java.io.IOException;
import java.sql.*;

public class DbUtil {
    public static Connection connect(String dbUrl, String dbId, String dbPassword)
        throws RuntimeException {

        Connection conn = null;

        //DBConfiguration dbConfiguration = DBConfiguration.getInstance();
        GetPropertyValue getPropertyValue = new GetPropertyValue();
        try {
            getPropertyValue.getPropValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            Class.forName(getPropertyValue.getDbDriver());

           // Class.forName(getPropertyValue.getDbDriver());


            conn = DriverManager.getConnection(dbUrl,dbId,dbPassword);
            System.out.println(dbUrl+","+dbId+","+dbPassword);

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

