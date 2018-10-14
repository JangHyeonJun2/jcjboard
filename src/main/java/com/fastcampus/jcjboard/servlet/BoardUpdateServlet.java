package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDaoRead;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDaoRead boardDaoRead = new BoardDaoRead();
        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("showBoardDO",boardDaoRead.getBoardDO(id));

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/update.jsp");
        dispatcher.forward(req,resp);

}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BoardDaoRead boardDaoRead = new BoardDaoRead();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nickname");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");


            BoardDO boardDO = new BoardDO();
            boardDO.setId(id);
            boardDO.setNickname(name);
            boardDO.setTitle(title);
            boardDO.setContent(content);

            String DBpassword= boardDaoRead.getDBpassword(boardDO);

            if(password.equals(DBpassword)){
                boardDaoRead.updateBoardDO(boardDO);
                req.setAttribute("showBoardDO",boardDO);
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/WEB-INF/views/read.jsp");
                dispatcher.forward(req,resp);
            }else {
                req.setAttribute("unvalidPassword",true);

                req.setAttribute("showBoardDO",boardDO);

                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/WEB-INF/views/update.jsp");
                dispatcher.forward(req,resp);
            }
    }

}

