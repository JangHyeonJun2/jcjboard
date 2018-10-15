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
    <title>게시판 - 글 수정</title>
</head>
<body>
<div class="ui container">
    <form class="ui form" method="post" accept-charset="UTF-8" action="/board/update">

        <input type="hidden" name="id" value="${requestScope.articleVO.id}"> <br>
        <input type="hidden" name="date" value="${requestScope.articleVO.date}"> <br>
        <div class="ui equal width form">
            <c:if test="${requestScope.get('unvalidPassword')}">
                <div class="ui negative message">
                    <i class="close icon"></i>
                    <div class="header">
                        비밀번호가 맞지 않습니다.
                    </div>
                    <p>다시 시도해주세요.</p>
                </div>
            </c:if>
            <div class="fields">
                <div class="field">
                    <label>닉네임</label>
                    <input type="text" value="${requestScope.articleVO.nickname}" placeholder="닉네임" name="nickname"  required>
                </div>
                <div class="field">
                    <label>비밀번호</label>
                    <input type="password" placeholder="비밀번호" name="password" required>
                </div>
            </div>
            <div class="field">
                <label>제목</label>
                <input type="text" value="${requestScope.articleVO.title}" placeholder="제목을 입력해주세요." name="title" required>
            </div>
            <div class="field">
                <label>내용</label>
                <textarea placeholder="내용을 입력해주세요." id="content" name="content" required>${requestScope.articleVO.content}</textarea>
            </div>

            <div>
                <button type="submit"  class="right floated ui primary button">
                    <i class="icon edit"></i>
                    등록
                </button>
                <button type="button" onclick="window.location.href='/board/read?id=${requestScope.articleVO.id}'" class="right floated ui button">
                    취소
                </button>
            </div>

        </div>
    </form>
</div>
</body>
</html>
