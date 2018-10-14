package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDaoRead;
import com.fastcampus.jcjboard.dao.CommentDaoRead;
import com.fastcampus.jcjboard.dao.CommentDaoWrite;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/read")
public class BoardReadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDaoRead boardDaoRead = new BoardDaoRead();
        int id = Integer.parseInt(req.getParameter("id"));

        BoardDO boardDO = boardDaoRead.getBoardDO(id);

        req.setAttribute("showBoardDO",boardDO);

        CommentDaoRead commentDaoRead = new CommentDaoRead();
        List<CommentVO> commentList = commentDaoRead.getCommentList(id);
        req.setAttribute("showComment",commentList);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/read.jsp");
        dispatcher.forward(req,resp);
    }
}
