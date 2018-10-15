package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.CommentDao;

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

        // commentList 가 만들어지면 그 때 적용해보자.
        // 일단은 1로 대체.
        int commentid = 0;
        try {
            commentid = Integer.parseInt(req.getParameter("commentid"));
        } catch (NumberFormatException e) {
            resp.sendRedirect("/board/read");
            /*id값 설정해야하는지 확인하기*/
            e.printStackTrace();
        }
        CommentVO comment = commentDao.getComment(commentid);
        //CommentVO comment = commentDaoUpdate.getCommentOne(1);

        req.setAttribute("comment",comment);
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/commentUpdate.jsp");
        dispatcher.forward(req,resp);




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // commentUpdate.jsp 에서 폼으로 액션 걸어주고 sendRedirect 마무리.

        req.setCharacterEncoding("UTF-8");

        int commentid = Integer.parseInt(req.getParameter("commentid"));
        int boardid = Integer.parseInt(req.getParameter("boardid"));

        // form 에 입력한 값을 가져오기.
        String content = req.getParameter("content");
        String nickname = req.getParameter("nickname");
        String date = req.getParameter("date");

        CommentVO comment = new CommentVO();

        comment.setCommentid(commentid);
        comment.setContent(content);
        comment.setNickname(nickname);
        comment.setDate(date);
        comment.setBoardid(boardid);

        CommentDao commentDao = new CommentDao();
        //commentDaoUpdate.updateComment(comment);

        // 해당 댓글의 비번을 디비에서 가져오는 부분.
        String dbPassword = commentDao.getCommentPassword(commentid);

        // 화면에 입력한 비밀번호 값을 가져오기.
        String password = req.getParameter("password");

        if(password.equals(dbPassword)) {
            commentDao.updateComment(comment);

        } else {
            // ------ 비번 틀렸을시 취해야할 행동.
            //패스워드가 틀린경우 다시 포워딩한다.
            req.setAttribute("unvalidPassword",true);
            req.setAttribute("boardid",boardid);

            req.setAttribute("comment",comment);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/commentUpdate.jsp");
            dispatcher.forward(req,resp);
        }

        resp.sendRedirect("/board/read?id="+boardid);

    }




}
