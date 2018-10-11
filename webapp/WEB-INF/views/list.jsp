<%--
  Created by IntelliJ IDEA.
  User: choijaeyong
  Date: 08/10/2018
  Time: 1:38 PM
  To change this template use File | Settings | File Templates.
--%>
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
    <title>게시판 - 목록</title>
</head>
<body style="padding-top: 20px">
<div class="ui container">
    <h2 class="ui header"> 게시판 목록 </h2>
    <div id="list">
        <table class="ui selectable blue striped celled padded table">
            <thead>
            <tr>
                <th class="one wide center aligned">번호</th>
                <th class="nine wide center aligned">제목</th>
                <th class="three wide center aligned">글쓴이</th>
                <th class="three wide center aligned">날짜</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.board}" var="bo">
                <tr>
                    <td class="center aligned">${bo.id}</td>
                    <td><a href="/board/read?id=${bo.id}"> ${bo.title}</a></td>
                    <td class="center aligned">${bo.nickname}</td>
                    <td class="center aligned">${bo.date}</td>
                </tr>
            </c:forEach>

            </tbody>

        </table>
        <div class="ui grid container">
            <div class="ui column centered grid">
                <div style="text-align: center" class="ui ten wide column left floated">
                    <!--
                        이전 버튼이 클릭가능한 조건이면, a태그를 이용해 이전 버튼이 뜨도록 하고, href로 링크를 걸되
                        아까 만든 makeQuery 메서드를 이용해서 쿼리문자열을 만들어 줍니다.
                        ?page=어쩌고&perPageNum=어쩌고 이 부분을 생성해서 넣게 되는데 단 이전 버튼을 클릭하면
                        현재 페이지가 시작페이지의 - 1 이 되도록 되어야 함으로 그 부분만 신경써 주면 됩니다.
                     -->
                    <c:if test="${paging.prev}">
            <span>
                <%--<a href="/board/listPage${pageMaker.makeQuery(pageMaker.startPage - 1)}">[이전]</a>--%>
                <a href="/board/list?page=${paging.startPage - 1}">[이전]</a>
            </span>

                    </c:if>

                    <!--
                        [1][2][3]....[10] 이 부분을 삽입하는 부분이다. jstl 이용해 for문을 돌면서 startPage ~ endPage까지
                        표시해주되, a태그 눌렀을 때 이동하는 page 부분에 index 지정하는 부분을 유의깁게 보길 바란다.
                     -->
                    <c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="index">
                        <%--<a href="/board/listPage${pageMaker.makeQuery(index) }">[${index }]</a>--%>
                        <a href="/board/list?page=${index}">[${index }]</a>
                    </c:forEach>

                    <c:if test="${paging.next }">
                        <!--
                        이전버튼과 마찬가지로 다음버튼을 끝 페이지보다 1개 큰게 현재 페이지가 되도록 makeQuery에 page를 넣어줍시다.
                        -->
                        <span>
                <%--<a href="/board/listPage${pageMaker.makeQuery(pageMaker.endPage + 1} }">[다음]</a>--%>
                <a href="/board/list?page=${paging.endPage + 1}">[다음]</a>
                 </span>
                    </c:if>
                </div>

                <button style="text-align: center" type="button" onclick="window.location.href='/board/write'" class="ui two wide column right floated primary button">
                    글쓰기
                </button>
            </div>

        </div>
</div>


</div>




</body>
</html>
