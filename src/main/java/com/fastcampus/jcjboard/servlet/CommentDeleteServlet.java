package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDao;
import com.fastcampus.jcjboard.util.InputValueHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/comment/delete")
public class CommentDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int commentid = InputValueHandler.convertToInt("commnetid", req, resp);
        int boardid = InputValueHandler.convertToInt("boardid", req, resp);

        req.setAttribute("commentid" , commentid);
        req.setAttribute("boardid" , boardid);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentDelete.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String password = req.getParameter("password");

        int commentid = InputValueHandler.convertToInt("commnetid", req, resp);
        int boardid = InputValueHandler.convertToInt("boardid", req, resp);

        CommentDao commentDaoDelete = new CommentDao();
        //패스워드가 틀린경우 다시 포워딩한다.
        if (commentDaoDelete.deleteComment(commentid,password)<=0) {
            req.setAttribute("unvalidPassword",true);
            req.setAttribute("commentid",commentid);
            req.setAttribute("boardid",boardid);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentDelete.jsp");
            dispatcher.forward(req,resp);
        }

        resp.sendRedirect("/board/read?id="+boardid);
    }
}
