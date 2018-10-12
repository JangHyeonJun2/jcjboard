<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018-10-08
  Time: 오후 4:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>두 번째 화면</title>
</head>
<body>
<form method="post" action="/board/read">
    <c:forEach items="${requestScope.showDetaile}" var="BoardDO">
        ID :${BoardDO.id}<br>
        NAME : ${BoardDO.nickname}<br>
        TITLE : ${BoardDO.title}<br>
        CONTENT : ${BoardDO.content}<br>
            <a href="/board/comment/write?id=${BoardDO.id}">댓글달기</a>
        <%--<c:forEach items="${requestScope.CommentList}" var="commentlist">--%>
            <%--이름 : ${commentlist.nickname}<br>--%>
            <%--내용 : ${commentlist.content}<br>--%>
        <%--</c:forEach>--%>
        <a href="/board/list">목록</a> <a href="/board/update?id=${BoardDO.id}">수정</a> <a href="/board/delete?id=${BoardDO.id}">삭제</a>
    </c:forEach>
</form>
</body>
</html>
