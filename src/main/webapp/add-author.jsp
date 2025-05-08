<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 07.05.2025
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Add New Author</h2>
<form id="addSongForm" action="${pageContext.request.contextPath}/author" method="post">
    <input type="text" id="name" name="name" placeholder="Name of the author" required>
    <input type="text" id="concertsLink" name="concertsLink" placeholder="Link to the concerts" required>

    <button type="submit">Add Author</button>

</form>
</body>
</html>
