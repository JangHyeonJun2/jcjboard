package com.fastcampus.jcjboard.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class DBConfigurationManager {
    private ObjectMapper objectMapper;

    //생성자는 private로 만든다. (싱글톤으로 만들예정)
    private DBConfigurationManager() {
        objectMapper = new ObjectMapper();
    }

    //static으로 DBconfigurationManager 객체생성
    private static DBConfigurationManager instance = new DBConfigurationManager();

    //외부에서 객체를 받아오는 public메소드
    public static DBConfigurationManager getInstance() {
        return instance;
    }

    public DBConfiguration getDBConfiguration(String fileName) {
        try {
            DBConfiguration dbConfiguration = objectMapper.readValue(new File(fileName), DBConfiguration.class);
            return dbConfiguration;
        } catch (Exception ex) {
            return null;
        }
    }
}
