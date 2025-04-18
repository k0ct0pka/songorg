<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 17.04.2025
  Time: 11:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user" method="post">
    <br>
    <label>
        Name:
        <br>
        <input type="text" name="name">
    </label>
    <br>
    <br>
    <label>
        Email:
        <br>
        <input type="text" name="email">
    </label>
    <br>
    <br>
    <label>
        Password:
        <br>
        <input type="password" name="password">
    </label>
    <br>

    <br>
    <input type="submit" value="Register" class="submit">
    <a href="${pageContext.request.contextPath}/login.jsp">Go To Registration</a>
</form>
</body>
</html>
