<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user" method="get">
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
    <input type="submit" value="Log In" class="submit">
    <a href="${pageContext.request.contextPath}/registration">Go To Registration</a>
</form>
</body>
</html>