<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/member/header.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/nstyle.css">
</head>
<body>


<table width="80%" border="1" class="notice">
    <tr style="border-bottom:2px solid black; text-align: center">
        <td>번호</td>
        <td style="text-align: left">제목</td>
        <td>글쓴이</td>
        <td>날짜</td>
        <td>조회</td>
    </tr>

    <c:forEach var="board" items="${requestScope.boards}">

    <tr style="border-bottom: 1px solid #ccc; text-align: center">
        <td style="width: 10%"><c:out value="${board.no}"/>
        </td>
        <td style="width: 65%; text-align: left">
            <a style="margin: 0" href="noticeDetail.do?no=<c:out value="${board.no}"/>">
                <c:out value="${board.title}"/>
            </a>
        </td>
        <td style="width: 15%"><c:out value="${board.writer}"/>
        </td>
        <td style="width: 15%"><c:out value="${board.date}"/>
        </td>
        <td style="width: 10%"><c:out value="${board.hit}"/>
        </td>
    </tr>
    </c:forEach>
</table>
<c:if test="${sessionScope.login != null}">
<a href="/board/noticeWrite.do">글쓰기</a>
</c:if>
<form action="/board/notice.do" method="get">
    <select name="select" id="">
        <option ${requestScope.select=="title"?"selected":""} value="title">제목</option>
        <option ${requestScope.select=="content"?"selected":""} value="content">내용</option>
    </select>
    <input type="text" name="search" value="${requestScope.search}">
    <input type="submit" value="검색">
</form>
</body>
</html>