<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 04.05.2025
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="addSongForm" action="${pageContext.request.contextPath}/add-song" method="post">
    <input type="text" id="name" name="name" placeholder="name of the song">
    <input type="text" id="link" name="link" placeholder="link to the song">
    <input type="text" id="genre" name="genre" placeholder="genre of the song">
    <select title="Available authors" id="select">
        <c:forEach var="author" items="${authors}">
            <option value="${author.id}">${author.name}</option>
        </c:forEach>
    </select>
    <div id="selected"></div>
    <button type="button" id="selectAut" onclick="selectAuthor()">Select author</button>
    <button type="button" onclick="addSong()">Add song</button>
</form>

<script>
    const selectAuthor = () => {
        const select = document.getElementById("select");
        const selectedDiv = document.getElementById("selected");
        const selectedOption = select.options[select.selectedIndex];

        const p = document.createElement("p");
        p.textContent = selectedOption.text;
        const inp = document.createElement("input");
        inp.type = "hidden";
        inp.name = "authorIds";  // important: so the server gets ?authorIds=1&authorIds=2
        inp.value = selectedOption.value;

        const removeBtn = document.createElement("button");
        removeBtn.textContent = "Удалить";
        removeBtn.type = "button";
        removeBtn.style.marginLeft = "10px";

        removeBtn.onclick = () => {
            const option = document.createElement("option");
            option.value = selectedOption.value;
            option.text = selectedOption.text;
            select.appendChild(option);
            selectedDiv.removeChild(p);
        };

        p.appendChild(removeBtn);
        p.appendChild(inp);
        selectedDiv.appendChild(p);

        select.remove(select.selectedIndex);
    };

    const addSong = () => {
        const form = document.getElementById("addSongForm");
        form.submit();  // отправляем GET-запрос с параметрами
    };
</script>
</body>
</html>
