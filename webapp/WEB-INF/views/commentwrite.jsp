<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018-10-12
  Time: 오후 1:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>댓글쓰기</title>
</head>
<body>
    <form method="post" action="/board/comment/write">
        이름 : <input type="text" name="nickname">

        비밀번호 : <input type="password" name="password">

        내용 : <textarea name="content"></textarea>

        현재 글의 ID를 "boardid"로 넘겨주어야함
        <button type="submit">댓글쓰기</button>
    </form>
</body>
</html>
