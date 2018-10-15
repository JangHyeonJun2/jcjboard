package com.fastcampus.jcjboard.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbConfProperty {

    private String DbUrl;
    private String DbUser;
    private String DbPassword;
    private String DbDriver;

    private static DbConfProperty dbConfProperty = new DbConfProperty();

    public static DbConfProperty getInstance(){
        return dbConfProperty;
    }
    private DbConfProperty(){
        Properties properties = new Properties();
        String proFileName = "DbConf";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(proFileName);

        if(inputStream != null){
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                throw new FileNotFoundException("property file" + proFileName + " not found in the classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        DbUrl = properties.getProperty("dbUrl");
        DbUser = properties.getProperty("dbId");
        DbPassword = properties.getProperty("dbPassword");
        DbDriver = properties.getProperty("dbDriver");
    }


    public String getDbUrl(){
        return DbUrl;
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
    public Class<DbConfProperty> getClassName(){return DbConfProperty.class;}

}
