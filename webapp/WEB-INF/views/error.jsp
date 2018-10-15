<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="head.jsp" flush="false"/>
    <title>게시판 - 문제가 발생했습니다.</title>
</head>
<body style="padding-top:20px">
<div class="ui container two column grid">
    <div class="ui column centered floated">
        <div class="ui error message">
            <div class="header">
                <p>죄송합니다.</p>
                <p>요청하신 페이지를 찾을 수 없습니다.</p>
            </div>
            <ul class="list">
                <li>방문하시려는 페이지의 주소가 잘못 입력되었거나,<br>
                    페이지의 주소가 변경 혹은 삭제되어 요청하신 페이지를 찾을 수 없습니다.<br><br>

                    입력하신 주소가 정확한지 다시 한번 확인해 주시기 바랍니다.</li><br>
                <li><a href="/board/list">게시판 목록으로 바로가기</a></li>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
