<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2018-10-08
  Time: 오후 4:21
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
    <title>게시판 - 글내용</title>
</head>
<body style="padding: 20px">
<div class="ui container">
<div class="ui container">
    <c:forEach items="${requestScope.showDetaile}" var="BoardDO">
        <div class="ui comments">
            <div class="comment">
                    <%--<a class="avatar">--%>
                    <%--<img src="/images/avatar/small/elliot.jpg">--%>
                    <%--</a>--%>
                <div class="content">
                    <a class="author">글쓴이 : ${BoardDO.nickname}</a>
                </div>
            </div>
        </div>

        <div class="ui message">
            <div class="header">
                    ${BoardDO.title}
            </div>
        </div>
        <div class="ui message" style="min-height: 220px">
            <p>${BoardDO.content}</p>
        </div>

        <div class="ui container">
            <div class="ui container">
            <button type="submit" onclick="window.location.href='/board/list'" class="left floated ui primary button">
                목록
            </button>
            </div>
            <div class="ui container">
            <button type="submit" onclick="window.location.href='/board/write'" class="right floated ui primary button">
                <i class="icon edit"></i>
                글쓰기
            </button>
            <button type="button" onclick="window.location.href='/board/update?id=${BoardDO.id}'" class="right floated ui button">
                수정
            </button>
            <button type="button" onclick="window.location.href='/board/delete?id=${BoardDO.id}'" class="right floated ui button">
                삭제
            </button>
            </div>
        </div>
    </c:forEach>
</div>



<div style="padding-top: 20px" class="ui container">
    <div class="ui divider" style="border-top: 1px solid rgba(255,255,255,.1);"></div>
    <div class="ui comments container">
        <h3 class="ui dividing header">댓글</h3>
    <c:forEach items="${requestScope.showComment}" var="show">
        <div class="comment">
            <%--<a class="avatar">--%>
            <%--<img src="/images/avatar/small/joe.jpg">--%>
            <%--</a>--%>
            <div class="content">
                <a class="author">${show.nickname}</a>
                <div class="metadata">
                    <div class="date">1 day ago</div>
                </div>
                <div class="text">
                    <p>${show.content}</p>
                </div>
                <div class="actions">
                    <a class="reply" href ="/board/comment/update?commentid=${show.commentid}">수정</a>
                    <a class="reply" href ="/board/comment/delete?commentid=${show.commentid}&boardid=${show.boardid}">삭제</a>
                </div>
            </div>
        </div>
    </c:forEach>
        <div class="ui divider"></div>
        <div style="margin-top:40px" class="ui container">
            <h4 class="ui header">댓글쓰기</h4>
            <c:forEach items="${requestScope.showDetaile}" var="BoardDO">
        <form class="ui reply form" method="post" action="/board/comment/write">
            <input type="hidden" name="boardid" value="${BoardDO.id}">
            <div class="fields">
                <div class="field">
                    <input type="text" placeholder="닉네임" name="nickname" required>
                </div>
                <div class="field">
                    <input type="password" placeholder="비밀번호" name="password" required>
                </div>
            </div>
            <div class="field">
                <textarea style="height:8em" name="content" placeholder="내용을 입력하세요." required></textarea>
            </div>

            <div class="ui container" style="padding-bottom: 40px">
            <button type="submit" class="right floated ui primary button">
                <i class="icon edit"></i>
                댓글 쓰기
            </button>
            </div>
        </form>
            </c:forEach>
        </div>
    </div>
</div>
</div>
</body>
</html>
