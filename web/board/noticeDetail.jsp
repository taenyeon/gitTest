<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/member/header.jsp" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../css/nstyle.css">
</head>
<body>
<div style="border: 1px solid #ccc; height: 50px ">
<span class="title">${requestScope.board.title}</span>
<span class="date">${requestScope.board.date}</span>
</div>

<table class="twidth">
    <colgroup>
        <col width="10%">
        <col width="100%">
    </colgroup>
    <tbody>
        <c:if test="${requestScope.files.size() > 0}">
        <c:forEach items="${requestScope.files}" var="file">
    <tr style="height: 10%">
        <th class="left">첨부파일</th>
        <td><a href="<c:url value="/board/noticeFileDown.do?filePath=${file.filePath}&fileName=${file.fileName}"/>" class="left"
               download>${file.fileName}
        </a>${file.fileSize} KB</td>
        </c:forEach>
        </c:if>
<c:if test="${requestScope.files.size() <0}">
        <th class="left">첨부파일</th>
        <td><a href="#">없음</a></td>
</c:if>
    </tr>
    <tr style="height: 90%">
        <td colspan="3" class="content">
            <pre>${requestScope.board.content}</pre>
        </td>
    </tr>
    </tbody>
</table>
<c:if test="${requestScope.writer}">
    <a href="/board/noticeEdit.do?no=${requestScope.board.no}">수정</a>
    <a href="/board/noticeDelete.do?no=${requestScope.board.no}">삭제</a>
</c:if>
<a href="/board/notice.do">목록</a>
</body>
</html>