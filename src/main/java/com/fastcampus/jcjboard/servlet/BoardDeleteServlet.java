package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDaoDelete;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //글의 패스워드가 DB에 저장된 패스워드가  맞는지 확인한다.
        String password = req.getParameter("password");
        int id = Integer.parseInt(req.getParameter("id"));

        BoardDaoDelete boardDaoDelete =new BoardDaoDelete();
        String DBpassword = boardDaoDelete.getDbPassword(id);

        if (password.equals(DBpassword)) {
            //패스워드가 맞다면, 해당 DB를 삭제한다.
            boardDaoDelete.deleteDB(id);
        }

        //게시판 목록으로 리다이렉트한다.
        resp.sendRedirect("/board/list");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}