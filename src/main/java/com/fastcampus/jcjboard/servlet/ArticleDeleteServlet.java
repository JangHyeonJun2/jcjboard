package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDao;
import com.fastcampus.jcjboard.util.InputValueHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/delete")
public class ArticleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //파라미터를 검사한다.
        //id를 Int로 바꿀수 있다면 바꾸고, 없으면 예외처리
        int id = InputValueHandler.convertToInt("id", req, resp);

        //id를 저장하고 req에 저장해둔다.
        req.setAttribute("id", id);

        //비밀번호 입력페이지로 포워딩한다.
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/articleDelete.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = InputValueHandler.convertToInt("id", req, resp);

        String password = req.getParameter("password");

        BoardDao boardDao =new BoardDao();

        //패스워드가 틀린경우 다시 포워딩한다. (삭제를 정상적으로 처리한 경우 리턴값이 1이상임)
        if (boardDao.deleteArticleVO(id, password)<=0) {
            req.setAttribute("unvalidPassword",true);
            req.setAttribute("id",id);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/articleDelete.jsp");
            dispatcher.forward(req,resp);
            return;
        }

        //게시판 목록으로 리다이렉트한다.
        resp.sendRedirect("/board/list");
    }
}
