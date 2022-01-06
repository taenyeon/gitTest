<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<html>
<head>
    <title>Title</title>
    <style>
        h2, form {
            text-align: center;
            margin: 30px;
        }
    </style>
</head>
<body>
<h2>Info</h2>
<form action="/member/userInfoUpdate.do?c=${sessionScope.login}" method="post">
    NAME : <input type="text" name="name" id="name" value="${requestScope.member.name}"> <br>
    TEL : <input type="text" name="tel" id="tel" value="${requestScope.member.tel}"> <br>
    EMAIL : <input type="email" name="email" id="email" value="${requestScope.member.email}"> <br>
    HABIT : <input type="text" name="habit" id="habit" value="${requestScope.member.habit}"> <br>
    BIRTH : <input type="date" name="birth" id="birth" value="${requestScope.member.birth}"> <br>
    GENDER :
    <label>
        <input type="checkbox" name="gender" value="M" ${requestScope.member.is_Man == true ? "checked":""}> M
    </label>
    <label>
        <input type="checkbox" name="gender" value="F" ${requestScope.member.is_Man == false ? "checked":""}> F
    </label> <br>
    IS_LUNAR :
    <label>
        <input type="checkbox" name="is_lunar" value="Y" ${requestScope.member.is_lunar == true ? "checked":""}> Y
    </label>
    <label>
        <input type="checkbox" name="is_lunar" value="N" ${requestScope.member.is_lunar == false ? "checked":""}> N
    </label> <br>
    <input type="submit" value="개인정보 수정">
</form>
</body>
</html>

