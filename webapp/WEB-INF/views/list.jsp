<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 08/10/2018
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h1> List </h1>


게시판 글이 몇개 있니???
${ boardSize } <br>


<div id="list">
    <table border="1px solid-black" >
        <thead>
            <tr>
                <th width ="80px">번호</th>
                <th width ="200px">제목</th>
                <th width ="100px">닉네임</th>
                <th width ="250px">날짜</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${requestScope.board}" var="bo">
                    <tr>
                        <td>${bo.id}</td>
                        <td>${bo.title}</td>
                        <td>${bo.nickname}</td>
                        <td>${bo.date}</td>
                    </tr>
                </c:forEach>

        </tbody>

    </table>

    <h2> paging~~~ </h2>

</div>




</body>
</html>
