<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 11/10/2018
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form method="post" action="/board/comment/delete">
    <input type="hidden" name="commentid" value="${requestScope.get("commentid")}">
    비밀번호 : <input type="password" name="password">
    <button type="submit">확인</button>
</form>

</body>
</html>
