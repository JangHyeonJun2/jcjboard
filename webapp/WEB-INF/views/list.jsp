<%@ page import="com.fastcampus.jcjboard.servlet.BoardDO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018-10-08
  Time: 오후 3:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>첫 번째 화면</title>
</head>
<body>
    <c:forEach items = "${requestScope.BoardList}" var="BoardDO">
    <!--ID :${BoardDO.id}<br>-->
    <!--Name : ${BoardDO.nickname}<br>-->
        제목 : <a href="/board/read?id=${BoardDO.id}">  ${BoardDO.title}</a><br><br>
    </c:forEach>

</body>
</html>
