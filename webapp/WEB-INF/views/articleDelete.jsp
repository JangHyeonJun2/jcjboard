<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="head.jsp" flush="false"/>
    <title>게시판 - 글 삭제</title>
</head>
<body style="padding-top:40px">
<div class="ui container three column grid">
    <div class="ui column centered floated">
        <form method="post" action="/board/delete" class="ui form">

            <c:if test="${requestScope.get('unvalidPassword')}">
            <div class="ui negative message">
                <i class="close icon"></i>
                <div class="header">
                    비밀번호가 맞지 않습니다.
                </div>
                <p>다시 시도해주세요.</p>
            </div>
            </c:if>

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
