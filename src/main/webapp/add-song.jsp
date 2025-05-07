<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Song</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            background-color: white;
            padding: 30px;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 500px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
            border-radius: 6px;
            border: 1px solid #ccc;
        }

        #selected p {
            background-color: #e6f7ff;
            padding: 8px;
            border-radius: 6px;
            margin-top: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        #selected button {
            background-color: #ff4d4f;
            border: none;
            color: white;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }

        .button-row {
            display: flex;
            justify-content: space-between;
            margin-top: 15px;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
        }

        button:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Add New Song</h2>
    <form id="addSongForm" action="${pageContext.request.contextPath}/add-song" method="post">
        <input type="text" id="name" name="name" placeholder="Name of the song" required>
        <input type="text" id="link" name="link" placeholder="Link to the song" required>
        <input type="text" id="genre" name="genre" placeholder="Genre of the song" required>

        <label for="select">Available authors:</label>
        <select id="select">
            <c:forEach var="author" items="${authors}">
                <option value="${author.id}">${author.name}</option>
            </c:forEach>
        </select>

        <div id="selected"></div>

        <div class="button-row">
            <button type="button" onclick="selectAuthor()">Select author</button>
            <button type="button" onclick="addSong()">Add song</button>
        </div>
    </form>
</div>

<script>
    const selectAuthor = () => {
        const select = document.getElementById("select");
        const selectedDiv = document.getElementById("selected");
        const selectedOption = select.options[select.selectedIndex];

        const p = document.createElement("p");
        p.textContent = selectedOption.text;
        const inp = document.createElement("input");
        inp.type = "hidden";
        inp.name = "authorIds";
        inp.value = selectedOption.value;

        const removeBtn = document.createElement("button");
        removeBtn.textContent = "Удалить";
        removeBtn.type = "button";

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
        document.getElementById("addSongForm").submit();
    };
</script>
</body>
</html>