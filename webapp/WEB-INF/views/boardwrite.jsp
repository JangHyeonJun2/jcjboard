<%--
  Created by IntelliJ IDEA.
  User: siyoon
  Date: 18. 10. 8
  Time: 오후 1:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>게시판 -글쓰기</title>
</head>
<body>

    <form method="post" action="/board/write">
        이름 :<input type="text" name="nickname"> <br>
        제목 :<input type="text" name="title"> <br>
        비밀번호 :<input type="password" name="password"> <br>
        내용 :<textarea name="content"></textarea> <br>
        <button type="submit">글쓰기</button>
    </form>

</body>
</html>
