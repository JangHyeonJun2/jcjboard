package com.fastcampus.jcjboard.servlet;

import java.time.LocalDateTime;

public class CommentVO {
    private int commentid;
    private int boardid;
    private String content;
    private LocalDateTime date;
    private String password;
    private String nickname;

    public CommentVO(){

    }

    public CommentVO(String content, String password, String nickname, int boardid) {
        this.content = content;
        this.password = password;
        this.nickname = nickname;
        this.boardid=boardid;
    }

    public int getCommentid() {
        return commentid;
    }

    public void setCommentid(int commentid) {
        this.commentid = commentid;
    }

    public int getBoardid() {
        return boardid;
    }

    public void setBoardid(int boardid) {
        this.boardid = boardid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}