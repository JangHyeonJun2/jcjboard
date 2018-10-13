package com.fastcampus.jcjboard.servlet;

import com.fastcampus.jcjboard.dao.BoardDaoRead;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        BoardDaoRead boardDaoRead = new BoardDaoRead();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("nickname");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        String password = req.getParameter("password");


            BoardDO boardDO = new BoardDO();
            boardDO.setId(id);
            boardDO.setNickname(name);
            boardDO.setTitle(title);
            boardDO.setContent(content);


            if(boardDaoRead.getpassword(boardDO)==true){
                System.out.println(boardDO.getNickname());
                boardDaoRead.updateBoardDO(boardDO);
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/WEB-INF/views/read.jsp");
                dispatcher.forward(req,resp);
                req.setAttribute("validpassword",id);//여기서 아이디를 넘겨주는 이유가
            }else {
                RequestDispatcher dispatcher =
                        req.getRequestDispatcher("/WEB-INF/views/update.jsp");
                dispatcher.forward(req,resp);
                req.setAttribute("unvalidpassword",id);
            }


        /*
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/views/list.jsp");
        dispatcher.forward(req,resp);
        */
        //비밀번호 맞을 값을 저장
//            HttpSession session = req.getSession();
//            session.setAttribute("validpassword","true");


    }

}

