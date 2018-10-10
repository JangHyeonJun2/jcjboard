package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDaoList;
import com.fastcampus.jcjboard.paging.Paging;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BoardDaoList dao = new BoardDaoList();
        List<BoardDO> list = dao.getBoardList();

        // 게시판 총 글 수.
        int totalCount = list.size();

        Paging paging = new Paging();
        paging.setPageNo(1);
        paging.setPageSize(10);
        paging.setTotalCount(totalCount);




        req.setAttribute("board" , list);
        req.setAttribute("boardSize" , list.size());
        req.setAttribute("paging" , paging);







        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req,resp);
    }
}
