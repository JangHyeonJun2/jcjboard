<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 11/10/2018
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

    <form method="post" accept-charset="UTF-8" action="/board/comment/update">
        <input type="hidden" name="commentid" value="${comment.commentid}"> <br>
        NICKNAME : <input type="text" name="nickname" value="${comment.nickname}"> <br>
        <!-- 수정 불가능한 영역으로 만들기 -->
        DATE : <input type="text" name="date" id="date" value="${comment.date}"> <br>
        CONTENT : <textarea type="text" name="content"> ${comment.content} </textarea> <br>


        수정완료: <button type="submit">확인</button><br>
        <input type="hidden" name="boardid" value="${comment.boardid}">
        <a href="/board/read?id=${comment.boardid}">목록</a>

    </form>




</body>
</html>
