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
<h2>Join</h2>
<form action="/member/join.do" method="post">
    ID : <input type="text" name="id" id="id"> <br>
    PASSWORD : <input type="password" name="pwd" id="pwd"> <br>
    NAME : <input type="text" name="name" id="name"> <br>
    TEL : <input type="text" name="tel" id="tel"> <br>
    EMAIL : <input type="email" name="email" id="email"> <br>
    HABIT : <input type="text" name="habit" id="habit"> <br>
    BIRTH : <input type="date" name="birth" id="birth"> <br>
    GENDER :
    <label>
        <input type="checkbox" name="gender" value="M"> M
    </label>
    <label>
        <input type="checkbox" name="gender" value="F"> F
    </label> <br>
    IS_LUNAR :
    <label>
        <input type="checkbox" name="is_lunar" value="Y"> Y
    </label>
    <label>
        <input type="checkbox" name="is_lunar" value="N"> N
    </label>

    <input type="submit" value="join">
</form>
</body>
</html>