package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/comment/write")
public class CommentWriteServlet extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //파라미터 받기
        String nickName = req.getParameter("nickname");
        String password = req.getParameter("password");
        String content = req.getParameter("content");
        int boardid = Integer.parseInt(req.getParameter("boardid"));

        //파라미터 검사
        //입력정보중 어느하나라도 널이라면, 바로 리다이렉트한다.
        if (nickName == "" || req.getParameter("boardid") == "" || password == "" || content == "") {
            resp.sendRedirect("/board/list");
            return;
        }


        //댓글 객체에 생성, 내용저장
        CommentVO commentVO = new CommentVO(content,password,nickName,boardid);

        //DB에저장
        CommentDao commentDao = new CommentDao();
        commentDao.addComment(commentVO);

        //해당 글로 리다이렉트
        resp.sendRedirect("/board/read?id="+boardid);


    }
}
