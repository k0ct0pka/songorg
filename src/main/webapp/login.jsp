<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            background-color: #121212;
            color: #e0e0e0;
            font-family: 'Courier New', Courier, monospace;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .login-container {
            background-color: #1e1e1e;
            padding: 40px;
            border-radius: 10px;
            border: 2px solid #ff007f;
            text-align: center;
            width: 300px;
        }
        .login-container h2 {
            color: #ff007f;
            margin-bottom: 20px;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 2px solid #ff007f;
            background-color: #2e2e2e;
            color: #e0e0e0;
            border-radius: 5px;
        }
        .login-container input[type="submit"],
        .login-container a {
            display: inline-block;
            margin: 10px 5px;
            padding: 10px 20px;
            border: 2px solid #ff007f;
            color: #ff007f;
            text-decoration: none;
            background-color: transparent;
            transition: 0.3s;
            border-radius: 5px;
        }
        .login-container input[type="submit"]:hover,
        .login-container a:hover {
            background-color: #ff007f;
            color: #121212;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2>★ Log In ★</h2>
    <form action="${pageContext.request.contextPath}/user" method="get">
        <label>
            Email:
            <br>
            <input type="text" name="email">
        </label>
        <br>
        <label>
            Password:
            <br>
            <input type="password" name="password">
        </label>
        <br>
        <input type="submit" value="Log In">
    </form>
    <a href="${pageContext.request.contextPath}/registration">Go To Registration</a>
</div>
</body>
</html>