<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp" flush="false"/>
    <title>게시판 - 글쓰기</title>
</head>
<body style="padding-top:20px">
<div class="ui container">
<form method="post" action="/board/write" class="ui form">
        <div class="ui equal width form">
            <div class="fields">
                <div class="field">
                    <label>닉네임</label>
                    <input type="text" placeholder="닉네임" name="nickname" required>
                </div>
                <div class="field">
                    <label>비밀번호</label>
                    <input type="password" placeholder="비밀번호" name="password" required>
                </div>
            </div>
            <div class="field">
                <label>제목</label>
                <input type="text" placeholder="제목을 입력해주세요." name="title" required>
            </div>
            <div class="field">
                <label>내용</label>
                <textarea placeholder="내용을 입력해주세요." name="content" required></textarea>
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

        </div>
</form>
</div>

</body>
</html>
