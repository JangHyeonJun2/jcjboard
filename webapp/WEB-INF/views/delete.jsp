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
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.js"></script>
    <title>게시판 - 글 삭제</title>
</head>
<body style="padding-top:40px">
<div class="ui container three column grid">
    <div class="ui column centered floated">
        <form method="post" action="/board/delete" class="ui form">
            <div class="field">
                <input type="hidden" name="id" value="${requestScope.get("id")}">
                <label>비밀번호를 입력하세요.</label>
                <input type="password" placeholder="비밀번호" name="password" required>
                <div style="margin-top:10px">
                    <button type="button" onclick="window.location.href='/board/list'" class="right floated ui button">
                        취소
                    </button>
                    <button type="submit" class="right floated ui primary button">
                        확인
                    </button>
                </div>
            </div>
        </form>
    </div>

</div>

</body>
</html>
