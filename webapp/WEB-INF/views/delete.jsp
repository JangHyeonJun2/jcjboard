<%--
  Created by IntelliJ IDEA.
  User: siyoon
  Date: 18. 10. 10
  Time: 오후 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>글 삭제</title>
</head>
<body>
<form method="post" action="/board/delete">
    <input type="hidden" name="id" value="${requestScope.get("id")}">
    비밀번호 : <input type="password" name="password">
    <button type="submit">확인</button>
</form>

</body>
</html>
