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

@WebServlet("/board/update")
public class BoardUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDaoRead boardDao = new BoardDaoRead();
        int id = Integer.parseInt(req.getParameter("id"));

        String sql = "select boardid,nickname,title,content from board where boardid="+id;//글번호 id로 해당 글의 제목과 내용을 조회해온다.
        List<BoardDO> list = boardDao.getBoardList(sql);

        req.setAttribute("showDetaile",list);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/update.jsp");
        dispatcher.forward(req,resp);

}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nickname");
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        //BoardDO boardDO = new BoardDO(id,title,content);
        BoardDO boardDO = new BoardDO();
        boardDO.setId(id);
        boardDO.setNickname(name);
        boardDO.setTitle(title);
        boardDO.setContent(content);
        BoardDaoRead boardDao = new BoardDaoRead();
        boardDao.updateBoardDO(boardDO);

        resp.sendRedirect("/board/list");
    }
}
