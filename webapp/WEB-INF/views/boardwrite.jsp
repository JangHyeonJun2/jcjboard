<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: siyoon
  Date: 18. 10. 8
  Time: 오후 1:04
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
    <title>게시판 -글쓰기</title>
</head>
<body style="padding-top:20px">
<div class="ui container">
<form method="post" action="/board/write">
        <div class="ui equal width form">
            <div class="fields">
                <div class="field">
                    <label>닉네임</label>
                    <input type="text" placeholder="닉네임" name="nickname">
                </div>
                <div class="field">
                    <label>비밀번호</label>
                    <input type="password" placeholder="비밀번호" name="password">
                </div>
            </div>
            <div class="field">
                <label>제목</label>
                <input type="text" placeholder="제목을 입력해주세요." name="title">
            </div>
            <div class="field">
                <label>내용</label>
                <textarea placeholder="내용을 입력해주세요." name="content"></textarea>
            </div>
            <button type="submit" class="ui primary button">
                등록
            </button>
            <button type="button" onclick="window.location.href='/board/list'" class="ui button">
                취소
            </button>
        </div>
</form>
</div>

</body>
</html>
