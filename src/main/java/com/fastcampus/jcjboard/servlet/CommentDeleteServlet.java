package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDaoDelete;
import com.fastcampus.jcjboard.dao.CommentDaoUpdate;

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
        int commentid = 0;
        int boardid=0;
        try {
            commentid = Integer.parseInt(req.getParameter("commentid"));
        }catch(NumberFormatException e) {
            // Dao 에서 boardid 가져오기.
            resp.sendRedirect("/board/read?id="+ boardid);
            return;
        }

        req.setAttribute("commentid" , commentid);
        req.setAttribute("boardid" , boardid);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentDelete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        int commentid = Integer.parseInt(req.getParameter("commentid"));
        int boardid = Integer.parseInt(req.getParameter("boardid"));

        CommentDaoDelete commentDaoDelete = new CommentDaoDelete();
        String dbPassword = commentDaoDelete.getCommentPassword(commentid);

        if(password.equals(dbPassword)) {
            commentDaoDelete.deleteComment(commentid);
        }

        resp.sendRedirect("/board/read?id="+boardid);


    }
}
