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
<body style="padding-top: 20px">
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
        <div class="ui message" style="min-height: 240px">
            <p>${BoardDO.content}</p>
        </div>

    <div class="ui container">
        <button type="submit" onclick="window.location.href='/board/list'" class="left floated ui primary button">
            목록
        </button>
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
    </c:forEach>
</div>
    <div style="padding-top: 25px" class="ui container grid">
    <div class="ui comments">
        <h3 class="ui dividing header">댓글</h3>
        <div class="comment">
            <%--<a class="avatar">--%>
                <%--<img src="/images/avatar/small/joe.jpg">--%>
            <%--</a>--%>
                <div class="content">
                    <a class="author">홍길</a>
                    <div class="metadata">
                        <div class="date">1 day ago</div>
                    </div>
                    <div class="text">
                        <p>하이 나는 동해물과 백두산이 마르고 닳도록 하나님이 보우하사 우리나라만 </p>
                        <p>재미있나요?</p>
                    </div>
                    <%--<div class="actions">--%>
                    <%--<a class="reply">Reply</a>--%>
                    <%--</div>--%>
                </div>
            <div class="content">
                <a class="author">Joe Henderson</a>
                <div class="metadata">
                    <div class="date">1 day ago</div>
                </div>
                <div class="text">
                    <p>The hours, minutes and seconds stand as visible reminders that your effort put them all there. </p>
                    <p>Preserve until your next run, when the watch lets you see how Impermanent your efforts are.</p>
                </div>
                <%--<div class="actions">--%>
                    <%--<a class="reply">Reply</a>--%>
                <%--</div>--%>
            </div>
        </div>

        <div class="comment">
            <%--<a class="avatar">--%>
                <%--<img src="/images/avatar/small/christian.jpg">--%>
            <%--</a>--%>
            <div class="content">
                <a class="author">Christian Rocha</a>
                <div class="metadata">
                    <div class="date">2 days ago</div>
                </div>
                <div class="text">
                    I re-tweeted this.
                </div>
                <%--<div class="actions">--%>
                    <%--<a class="reply">Reply</a>--%>
                <%--</div>--%>
            </div>
        </div>
        <form class="ui reply form">
            <div class="field">
                <textarea></textarea>
            </div>
            <div class="ui primary submit labeled icon button">
                <i class="icon edit"></i> Add Comment
            </div>
        </form>

    </div>
</div>
</body>
</html>
