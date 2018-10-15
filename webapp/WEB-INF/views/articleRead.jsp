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
    <title>게시판 - 글내용</title>
</head>
<body style="padding: 20px">
<div class="ui container">
<div class="ui container">

        <div class="ui comments">
            <div class="comment">
                    <%--<a class="avatar">--%>
                    <%--<img src="/images/avatar/small/elliot.jpg">--%>
                    <%--</a>--%>
                <div class="content">
                    <a class="author">글쓴이 : ${requestScope.articleVO.nickname}</a>
                    <div class="metadata">
                        <div class="date">날짜 : ${requestScope.articleVO.date}</div>
                        <div class="date">조회수 ${requestScope.articleVO.viewCount}</div>
                    </div>
                </div>
            </div>
        </div>

        <div class="ui message">
            <div class="header">
                    ${requestScope.articleVO.title}
            </div>
        </div>
        <div class="ui message" style="min-height: 150px">
            <p>${requestScope.articleVO.content}</p>
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
            <button type="button" onclick="window.location.href='/board/update?id=${requestScope.articleVO.id}'" class="right floated ui button">
                수정
            </button>
            <button type="button" onclick="window.location.href='/board/delete?id=${requestScope.articleVO.id}'" class="right floated ui button">
                삭제
            </button>
            </div>
        </div>

</div>



<div style="padding-top: 20px" class="ui container">
    <div class="ui divider" style="border-top: 1px solid rgba(255,255,255,.1);"></div>
    <div class="ui comments container">
        <c:if test="${requestScope.get('commentList').size()>0}">
        <h3 class="ui dividing header">댓글</h3>
        </c:if>
    <c:forEach items="${requestScope.commentList}" var="comment">
        <div class="comment">
            <%--<a class="avatar">--%>
            <%--<img src="/images/avatar/small/joe.jpg">--%>
            <%--</a>--%>
            <div class="content">
                <a class="author">${comment.nickname}</a>
                <div class="metadata">
                    <div class="date">${comment.date}</div>
                </div>
                <div class="text">
                    <p>${comment.content}</p>
                </div>
                <div class="actions">
                    <a class="reply" href ="/board/comment/update?commentid=${comment.commentid}">수정</a>
                    <a class="reply" href ="/board/comment/delete?commentid=${comment.commentid}&boardid=${comment.boardid}">삭제</a>
                </div>
            </div>
        </div>
        <div class="ui divider"></div>
    </c:forEach>
<c:if test="${requestScope.get('commentList').size()<=0}">
        <div class="ui divider"></div>
</c:if>
        <div style="margin-top:40px" class="ui container">
            <h4 class="ui header">댓글쓰기</h4>

        <form class="ui reply form" method="post" action="/board/comment/write">
            <input type="hidden" name="boardid" value="${requestScope.articleVO.id}">
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
        </div>
    </div>
</div>
</div>
</body>
</html>
