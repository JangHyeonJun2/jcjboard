<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 11/10/2018
  Time: 12:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <form method="post" accept-charset="UTF-8" action="/board/comment/update">


        <c:if test="${requestScope.get('unvalidPassword')}">
            <div class="ui negative message">
                <i class="close icon"></i>
                <div class="header">
                    비밀번호가 맞지 않습니다.
                </div>
                <p>다시 시도해주세요.</p>
            </div>
        </c:if>

        <input type="hidden" name="commentid" value="${comment.commentid}"> <br>
        NICKNAME : <input type="text" name="nickname" value="${comment.nickname}"> <br>
        <!-- 수정 불가능한 영역으로 만들기 -->
        DATE : <input type="text" name="date" id="date" value="${comment.date}"> <br>
        CONTENT : <textarea type="text" name="content"> ${comment.content} </textarea> <br>
        PASSWORD : <input type="text" name="password" value=""> <br>


        수정완료: <button type="submit">확인</button><br>
        <input type="hidden" name="boardid" value="${comment.boardid}">
        <button type="button" onclick="window.location.href='/board/read?id='+${comment.boardid}">
            목록
        </button>

    </form>

    <a href ="/board/comment/delete?commentid=${comment.commentid}&boardid=${comment.boardid}">
    <button type="button">삭제</button></a>

    <%--<form method="post" accept-charset="UTF-8" action="/board/comment/delete">--%>
        <%--<input type="hidden" name="commentid" value="${comment.commentid}"> <br>--%>
        <%--<input type="hidden" name="boardid" value="${comment.boardid}"> <br>--%>
        <%--<button type="submit">삭제!</button>--%>
    <%--</form>--%>

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
