package com.fastcampus.jcjboard.servlet;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class ArticleVO {
    private int id;
    private  String title;
    private  String content;
    private String date;
    private String password;
    private String nickname;
    private int commentCount = 0;
    private int viewCount;

    public ArticleVO(){

    }

    public ArticleVO(String title, String content, String nickname) {
        this.title = title;
        this.content = content;
        this.nickname = nickname;
    }

    public ArticleVO(String title, String content, String password, String nickname) {
        this(title, content, nickname);
        this.password = password;
    }

    public ArticleVO(int id, String title, String content, String date, String nickname) {
        this(title, content, nickname);
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
