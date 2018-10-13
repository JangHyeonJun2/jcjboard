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
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.js"></script>


    <title>Title</title>
</head>
<body>

<form method="post" action="/board/comment/delete">
    <input type="hidden" name="commentid" value="${requestScope.get("commentid")}">
    <input type="hidden" name="boardid" value="${requestScope.get("boardid")}">

    <c:if test="${requestScope.get('unvalidPassword')}">
        <div class="ui negative message">
            <i class="close icon"></i>
            <div class="header">
                비밀번호가 맞지 않습니다.
            </div>
            <p>다시 시도해주세요.</p>
        </div>
    </c:if>



    비밀번호 : <input type="password" name="password">
    <button type="submit">확인</button>
</form>

</body>



<script>
    $('.message .close')
        .on('click', function() {
            $(this)
                .closest('.message')
                .transition('fade')
            ;
        })
    ;</script>

</html>
