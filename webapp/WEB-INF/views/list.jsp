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


<a href="/board/write"><input type="button" value="Write"></a>



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
                        <td><a href="/board/read?id=${bo.id}"> ${bo.title}</a></td>
                        <td>${bo.nickname}</td>
                        <td>${bo.date}</td>
                    </tr>
                </c:forEach>

        </tbody>

    </table>

    <h2> paging~~~ </h2>

    <div class="paginate">
        <a href="" class="first">처음 페이지</a>
        <a href="" class="prev">이전 페이지</a>
        <span>
        <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq paging.pageNo}"><a href="" class="choice">${i}</a></c:when>
                <c:otherwise><a href="">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
        </span>
        <a href="" class="next">다음 페이지</a>
        <a href="" class="last">마지막 페이지</a>
    </div>


</div>




</body>
</html>
