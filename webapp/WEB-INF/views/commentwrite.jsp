<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>댓글쓰기</title>
</head>
<body>
    <form method="post" action="/board/comment/write">
        이름 : <input type="text" name="nickname">

        비밀번호 : <input type="password" name="password">

        내용 : <textarea name="content"></textarea>


        <button type="submit">댓글쓰기</button>
    </form>
</body>
</html>
