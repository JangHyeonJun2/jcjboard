package com.fastcampus.jcjboard.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/comment/write")
public class CommentWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //테스트페이지 전용 사실상 필요없음

        //댓글 데모페이지로 이동
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentWriteDemo.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        //파라미터 받기
        String nickname = req.getParameter("nickname");
        String password = req.getParameter("password");
        String content = req.getParameter("content");
        int boardid = Integer.parseInt(req.getParameter("boardid"));

        //파라미터 검사
        //입력정보중 어느하나라도 널이라면, 바로 리다이렉트한다.
        if (nickname == null || req.getParameter("boardid") == null || password == null || content == null) {
            resp.sendRedirect("/board/list");
            return;
        }

        //DB에저장

        //해당 글로 리다이렉트
        resp.sendRedirect("/board/read?id="+boardid);


    }
}
