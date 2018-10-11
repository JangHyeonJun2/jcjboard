package com.fastcampus.jcjboard.servlet;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyValue {
    InputStream inputStream;
    String DbUri;
    String DbUser;
    String DbPassword;
    String DbDriver;
    public void getPropValues() throws IOException{
        Properties properties = new Properties();
        String proFileName = "db2";

        inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);

        if(inputStream != null){
            properties.load(inputStream);
        }else{
            throw new FileNotFoundException("property file" + proFileName + " not found in the classpath");
        }
        DbUri = properties.getProperty("dbUrl");
        DbUser = properties.getProperty("dbId");
        DbPassword = properties.getProperty("dbPassword");
        DbDriver = properties.getProperty("dbDriver");

    }
    public String getDbUri(){
        return DbUri;
    }
    public String getDbUser(){
        return DbUser;
    }
    public String getDbPassword(){
        return DbPassword;
    }
    public String getDbDriver(){
        return DbDriver;
    }
}
