package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDao;
import com.fastcampus.jcjboard.util.InputValueHandler;

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
        int boardid = InputValueHandler.convertToInt("boardid", req, resp);

        //입력정보를 검사한다.
        //문자열 입력정보중 어느하나라도 ""(빈칸)이거나 null이라면, 에러페이지로 리다이렉트한다.
        if (InputValueHandler.isEmpty(nickName,password,content)) {
            resp.sendRedirect("/board/error");
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
