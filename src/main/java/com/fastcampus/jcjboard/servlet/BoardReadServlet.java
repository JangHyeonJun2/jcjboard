package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDao;

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
        BoardDao boardDao = new BoardDao();
        int id = Integer.parseInt(req.getParameter("id"));

        String sql = "select boardid,nickname,title,content from board where boardid="+id;//글번호 id로 해당 글의 제목과 내용을 조회해온다.
        List<BoardDO> list = boardDao.getBoardList(sql);

        req.setAttribute("showDetaile",list);


//        BoardDaoComment boardDaoComment = new BoardDaoComment();
//        List<BoardCommentDO> list2 = boardDaoComment.getBoardCommentList();
//        boardDaoComment.getBoardCommentList();
//        req.setAttribute("CommentList",list2);
//
//        RequestDispatcher dispatcher =
//                req.getRequestDispatcher("/WEB-INF/views/detail.jsp");
//        dispatcher.forward(req,resp);
    }
}
