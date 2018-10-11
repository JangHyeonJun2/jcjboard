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
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.css">
    <script
            src="https://code.jquery.com/jquery-3.1.1.min.js"
            integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/semantic-ui/2.3.3/semantic.min.js"></script>
    <title>게시판 - 글 수정</title>
</head>
<body>
<div class="ui container">
    <form class="ui form" method="post" accept-charset="UTF-8" action="/board/update">
        <c:forEach items="${requestScope.showDetaile}" var="BoardDO">
        <input type="hidden" name="id" value="${BoardDO.id}"> <br>
        <div class="ui equal width form">
            <div class="fields">
                <div class="field">
                    <label>닉네임</label>
                    <input type="text" value="${BoardDO.nickname}" placeholder="닉네임" name="nickname"  required>
                </div>
                <div class="field">
                    <label>비밀번호</label>
                    <input type="password" placeholder="비밀번호" name="password" required>
                </div>
            </div>
            <div class="field">
                <label>제목</label>
                <input type="text" value="${BoardDO.title}" placeholder="제목을 입력해주세요." name="title" required>
            </div>
            <div class="field">
                <label>내용</label>
                <textarea placeholder="내용을 입력해주세요." id="content" name="content" required>${BoardDO.content}</textarea>
            </div>

            <div>
                <button type="submit" class="right floated ui primary button">
                    <i class="icon edit"></i>
                    등록
                </button>
                <button type="button" onclick="window.location.href='/board/list'" class="right floated ui button">
                    취소
                </button>
            </div>
    </c:forEach>
        </div>
    </form>
</div>
</body>
</html>
