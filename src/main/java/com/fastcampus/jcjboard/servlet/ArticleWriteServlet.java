package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/write")
public class ArticleWriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //쓰기 화면을 보여주는 boardwrite.jsp로 포워딩
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/articleWrite.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //글쓰기 폼태그로부터 글 입력정보를 받는다.
        String nickName = req.getParameter("nickname");
        String title = req.getParameter("title");
        String password = req.getParameter("password");
        String content = req.getParameter("content");

        //입력정보를 검사한다.
        //입력정보중 어느하나라도 ""(빈칸)이라면, 바로 리다이렉트한다.
        if (nickName.equals("") || title.equals("") || password.equals("") || content.equals("")) {
            resp.sendRedirect("/board/list");
            return;
        }

        //입력정보를 받아 BoardDO를 생성한다
        ArticleVO articleVO = new ArticleVO(title,content,password,nickName);

        //DB에 저장한다.
        BoardDao boardDao = new BoardDao();
        boardDao.addArticleVO(articleVO);

        //게시판 목록으로 리다이렉트한다.
        resp.sendRedirect("/board/list");
    }
}
