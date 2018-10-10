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
    <title>Title</title>
</head>
<body>

<h1> List </h1>


게시판 글이 몇개 있니???
${ boardSize } <br>


<a href="/board/write"><input type="button" value="Write"></a>



<div id="list">
    <table border="1px solid-black" >
        <thead>
            <tr>
                <th width ="80px">번호</th>
                <th width ="200px">제목</th>
                <th width ="100px">닉네임</th>
                <th width ="250px">날짜</th>
            </tr>
        </thead>
        <tbody>
                <c:forEach items="${requestScope.board}" var="bo">
                    <tr>
                        <td>${bo.id}</td>
                        <td><a href="/board/read?id=${bo.id}"> ${bo.title}</a></td>
                        <td>${bo.nickname}</td>
                        <td>${bo.date}</td>
                    </tr>
                </c:forEach>

        </tbody>

    </table>

    <h2> paging~~~ </h2>

    <div class="pagination">
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

















    <%--<div class="paginate">--%>
        <%--<a href="" class="first">처음 </a>--%>
        <%--<a href="" class="prev">이전 </a>--%>
        <%--<span>--%>
        <%--<c:forEach var="i" begin="${paging.startPage}" end="${paging.endPage}" step="1">--%>
            <%--<c:choose>--%>
                <%--<c:when test="${i eq paging.pageNo}"><a href="" class="choice">${i}</a></c:when>--%>
                <%--<c:otherwise><a href="">${i}</a></c:otherwise>--%>
            <%--</c:choose>--%>
        <%--</c:forEach>--%>
        <%--</span>--%>
        <%--<a href="" class="next">다음 </a>--%>
        <%--<a href="" class="last">마지막 </a>--%>
    <%--</div>--%>


</div>




</body>
</html>
