<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
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
        .register-container {
            background-color: #1e1e1e;
            padding: 40px;
            border-radius: 10px;
            border: 2px solid #ff007f;
            text-align: center;
            width: 300px;
        }
        .register-container h2 {
            color: #ff007f;
            margin-bottom: 20px;
        }
        .register-container input[type="text"],
        .register-container input[type="password"] {
            width: 90%;
            padding: 10px;
            margin: 10px 0;
            border: 2px solid #ff007f;
            background-color: #2e2e2e;
            color: #e0e0e0;
            border-radius: 5px;
        }
        .register-container input[type="submit"],
        .register-container a {
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
        .register-container input[type="submit"]:hover,
        .register-container a:hover {
            background-color: #ff007f;
            color: #121212;
        }
    </style>
</head>
<body>
<div class="register-container">
    <h2>★ Register ★</h2>
    <form action="${pageContext.request.contextPath}/user" method="post">
        <label>
            Name:
            <br>
            <input type="text" name="name">
        </label>
        <br>
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
        <input type="submit" value="Register">
    </form>
    <a href="${pageContext.request.contextPath}/login.jsp">Go To Login</a>
</div>
</body>
</html>