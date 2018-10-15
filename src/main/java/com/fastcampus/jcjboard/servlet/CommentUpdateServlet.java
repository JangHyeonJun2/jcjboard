package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDao;
import com.fastcampus.jcjboard.util.InputValueHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/board/comment/update")
public class CommentUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 데이터를 읽어와 jsp 파일에 써주는 역할.
        CommentDao commentDao = new CommentDao();

        int commentid = InputValueHandler.convertToInt("commentid", req, resp);

        CommentVO comment = commentDao.getComment(commentid);

        req.setAttribute("comment",comment);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/commentUpdate.jsp");
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // commentUpdate.jsp 에서 폼으로 액션 걸어주고 sendRedirect 마무리.

        req.setCharacterEncoding("UTF-8");

        int commentid = InputValueHandler.convertToInt("commentid", req, resp);
        int boardid = InputValueHandler.convertToInt("boardid", req, resp);

        // form 에 입력한 값을 가져오기.
        String content = req.getParameter("content");
        String nickname = req.getParameter("nickname");
        String date = req.getParameter("date");
        String password = req.getParameter("password");

        //입력정보를 검사한다.
        //문자열 입력정보중 어느하나라도 ""(빈칸)이거나 null이라면, 에러페이지로 리다이렉트한다.
        if (InputValueHandler.isEmpty(content,nickname,date,password)) {
            System.out.println("문자열 에러 발생");
            resp.sendRedirect("/board/error");
            return;
        }

        CommentVO comment = new CommentVO();

        comment.setCommentid(commentid);
        comment.setContent(content);
        comment.setNickname(nickname);
        comment.setDate(date);
        comment.setBoardid(boardid);

        CommentDao commentDao = new CommentDao();

        //패스워드가 틀린경우 다시 포워딩한다.
        if (commentDao.updateComment(comment, password)<=0) {
            req.setAttribute("unvalidPassword",true);
            req.setAttribute("boardid",boardid);
            req.setAttribute("comment",comment);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentUpdate.jsp");
            dispatcher.forward(req,resp);
        }

        resp.sendRedirect("/board/read?id="+boardid);
    }

}
