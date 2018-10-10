<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018-10-10
  Time: 오전 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>세 번째 화면</title>
</head>
<body>
    <form method="post" accept-charset="UTF-8" action="/board/update">
    <c:forEach items="${requestScope.showDetaile}" var="BoardDO">
    <input type="hidden" name="id" value="${BoardDO.id}"> <br>
    NAME : <input type="text" name="nickname" value="${BoardDO.nickname}"> <br>
    TITLE : <input type="text" name="title" value="${BoardDO.title}"> <br>
        CONTENT : <br><textarea name="content" id="content" cols="30" rows="30" rows=""10>${BoardDO.content}</textarea><br>

        수정완료: <button type="submit">확인</button><br>
        <a href="/board/list">목록</a>
    </c:forEach>
    </form>
</body>
</html>
