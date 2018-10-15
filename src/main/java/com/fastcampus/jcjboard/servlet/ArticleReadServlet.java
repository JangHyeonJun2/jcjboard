package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDao;
import com.fastcampus.jcjboard.dao.CommentDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/read")
public class ArticleReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDao boardDao = new BoardDao();

        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            //id가 정수가 아닌경우 목록으로 리다이렉트
            resp.sendRedirect("/board/list");
            return;
        }

        ArticleVO articleVO = boardDao.getArticleVO(id);

        req.setAttribute("articleVO", articleVO);

        CommentDao commentDao = new CommentDao();
        List<CommentVO> commentList = commentDao.getCommentList(id);
        req.setAttribute("commentList",commentList);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/articleRead.jsp");
        dispatcher.forward(req,resp);
    }
}
