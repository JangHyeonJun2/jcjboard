<%--
  Created by IntelliJ IDEA.
  User: siyoon
  Date: 18. 10. 11
  Time: 오후 12:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>댓글쓰기 데모페이지</title>
</head>
<body>

<form method="post" action="/board/comment/write">
    이름 : <input type="text" name="nickname"><br>
    비밀번호 : <input type="password" name="password"> <br>
    내용 : <textarea name="content"></textarea><br>
    글ID(데모용) : <input type="number" name="boardid"><br>
    <button type="submit">댓글쓰기</button>
</form>
</body>
</html>
