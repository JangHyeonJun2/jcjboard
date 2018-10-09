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
        String id = req.getParameter("id");

        /**/
        //Daoread 완성되면, 그걸로 DBpassword 가져오기
        //해당 id의 passwword로
         /**/
        String DBpassword = password; //현재는 무조건 삭제

        if (password.equals(DBpassword)) {
            //패스워드가 맞다면, 해당 DB를 삭제한다.
            BoardDaoDelete boardDaoDelete =new BoardDaoDelete();
            boardDaoDelete.deleteDB(id);
        }

        //게시판 목록으로 리다이렉트한다.
        resp.sendRedirect("/board/list");
    }
}
