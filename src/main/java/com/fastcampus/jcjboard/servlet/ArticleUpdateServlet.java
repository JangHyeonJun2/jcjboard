package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDao;
import com.fastcampus.jcjboard.dao.CommentDao;
import com.fastcampus.jcjboard.util.InputValueHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/board/update")
public class ArticleUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoardDao boardDao = new BoardDao();
        int id = InputValueHandler.convertToInt("id", req, resp);

        req.setAttribute("articleVO",boardDao.getArticleVO(id));

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/articleUpdate.jsp");
        dispatcher.forward(req,resp);
}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        BoardDao boardDao = new BoardDao();
        int id = InputValueHandler.convertToInt("id", req, resp);

        String name = req.getParameter("nickname");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");
        String date = req.getParameter("date");

        //입력정보를 검사한다.
        //문자열 입력정보중 어느하나라도 ""(빈칸)이거나 null이라면, 에러페이지로 리다이렉트한다.
        if (InputValueHandler.isEmpty(name, title, content, password, date)) {
            System.out.println("문자열 에러발생");
            resp.sendRedirect("/board/error");
            return;
        }


        ArticleVO articleVO = new ArticleVO(id, title, content, date, name);

        //패스워드가 달라서 정상적으로 처리되지 않은 경우
        //(업데이트가 정상적으로 수행된경우 1건수정하므로 처리결과가 1이상이여야함)
        if(boardDao.updateArticleVO(articleVO,password)<=0){
            req.setAttribute("unvalidPassword",true);
            req.setAttribute("articleVO", articleVO);

            RequestDispatcher dispatcher =
                    req.getRequestDispatcher("/WEB-INF/views/articleUpdate.jsp");
            dispatcher.forward(req,resp);
            return;
        }

        CommentDao commentDao = new CommentDao();
        List<CommentVO> commentList = commentDao.getCommentList(id);

        req.setAttribute("articleVO", articleVO);
        req.setAttribute("commentList",commentList);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/articleRead.jsp");
        dispatcher.forward(req,resp);

    }

}

