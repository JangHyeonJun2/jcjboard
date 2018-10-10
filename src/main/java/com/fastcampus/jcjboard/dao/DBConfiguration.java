package com.fastcampus.jcjboard.dao;

public class DBConfiguration {
    private String dbUrl = "jdbc:mariadb://localhost:3306/Test_db";
    private String dbId = "siyoon";
    private String dbPassword = "1234";
    private String dbDriver = "org.mariadb.jdbc.Driver";

    //싱글톤으로 만듬
    private DBConfiguration() {

    }

    private static DBConfiguration dbConfiguration = new DBConfiguration();

    public static DBConfiguration getInstance() {
        return dbConfiguration;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbId() {
        return dbId;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbDriver() {
        return dbDriver;
    }
}
