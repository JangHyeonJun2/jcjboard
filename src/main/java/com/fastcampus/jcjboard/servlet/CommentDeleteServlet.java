package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDao;

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
        commentid = Integer.parseInt(req.getParameter("commentid"));
        // 파라미터를 하나 더 쓰지 않는 방법!
        // commentid 를 이용. db에서 select ~~~ where=commentid;
        boardid = Integer.parseInt(req.getParameter("boardid"));
//        try {
//
//        }catch(NumberFormatException e) {
//            // Dao 에서 boardid 가져오기.
//            resp.sendRedirect("/board/read?id="+ boardid);
//            return;
//        }

        req.setAttribute("commentid" , commentid);
        req.setAttribute("boardid" , boardid);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentDelete.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String password = req.getParameter("password");

        int commentid = Integer.parseInt(req.getParameter("commentid"));
        int boardid = Integer.parseInt(req.getParameter("boardid"));

        CommentDao commentDaoDelete = new CommentDao();
        String dbPassword = commentDaoDelete.getCommentPassword(commentid);

        if(password.equals(dbPassword)) {
            commentDaoDelete.deleteComment(commentid);

        } else {
            // ------ 비번 틀렸을시 취해야할 행동.
            //패스워드가 틀린경우 다시 포워딩한다.
            req.setAttribute("unvalidPassword",true);
            req.setAttribute("commentid",commentid);
            req.setAttribute("boardid",boardid);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentDelete.jsp");
            dispatcher.forward(req,resp);
        }

        resp.sendRedirect("/board/read?id="+boardid);
    }
}
