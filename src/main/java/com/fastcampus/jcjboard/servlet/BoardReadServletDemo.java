package com.fastcampus.jcjboard.servlet;


import com.fastcampus.jcjboard.dao.BoardDaoRead;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/readdemo")
public class BoardReadServletDemo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDaoRead boardDao = new BoardDaoRead();
        int id = Integer.parseInt(req.getParameter("id"));

        String sql = "select boardid,nickname,title,content from board where boardid="+id;//글번호 id로 해당 글의 제목과 내용을 조회해온다.
        List<BoardDO> list = boardDao.getBoardList(sql);

        req.setAttribute("showDetaile",list);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/detailDemo.jsp");
        dispatcher.forward(req,resp);
    }
}
