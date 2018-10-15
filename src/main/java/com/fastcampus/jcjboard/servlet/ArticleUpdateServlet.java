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

@WebServlet("/board/update")
public class ArticleUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDao boardDao = new BoardDao();
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("/board/read");
            /*id값이 제대로 들어갔는지 확인하기 = 본래의 글로 리다이렉트가 잘 되는지*/
            return;
        }

        req.setAttribute("articleVO",boardDao.getArticleVO(id));

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/articleUpdate.jsp");
        dispatcher.forward(req,resp);
}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BoardDao boardDao = new BoardDao();
        int id;
        try {
            id = Integer.parseInt(req.getParameter("id"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("/board/read");
            /*id값이 제대로 들어갔는지 확인하기 = 본래의 글로 리다이렉트가 잘 되는지*/
            return;
        }
        String name = req.getParameter("nickname");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");
        String date = req.getParameter("date");

        ArticleVO articleVO = new ArticleVO(id, title, content, date, name);

        String dBpassword= boardDao.getDbPassword(articleVO.getId());

            if(password.equals(dBpassword)){
                boardDao.updateArticleVO(articleVO);
                req.setAttribute("articleVO", articleVO);

                CommentDao commentDao = new CommentDao();
                List<CommentVO> commentList = commentDao.getCommentList(id);
                req.setAttribute("commentList",commentList);

                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/WEB-INF/views/articleRead.jsp");
                dispatcher.forward(req,resp);
            }else {
                req.setAttribute("unvalidPassword",true);
                req.setAttribute("articleVO", articleVO);

                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/WEB-INF/views/articleUpdate.jsp");
                dispatcher.forward(req,resp);
            }
    }

}

