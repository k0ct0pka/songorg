<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Post-Punk Music Hub</title>
    <style>
        body {
            background-color: #121212; /* Тёмный фон */
            color: #e0e0e0;
            font-family: 'Courier New', Courier, monospace;
            margin: 0;
            padding: 0;
        }
        header {
            text-align: center;
            padding: 20px;
            font-size: 2em;
            color: #ff007f; /* Яркий розовый */
        }
        .song-table {
            margin: 30px auto;
            width: 50%;
            border-collapse: collapse;
            background-color: #1e1e1e;
            border: 2px solid #ff007f;
            border-radius: 10px;
            overflow: hidden;
        }
        .song-table th, .song-table td {
            padding: 15px;
            text-align: center;
            border-bottom: 1px solid #333;
        }
        .song-table th {
            background-color: #2e2e2e;
            font-size: 1.2em;
        }
        .star {

                cursor: pointer;
                font-size: 24px;

                transition: color 0.3s ease;


            color: #FFD700; /* золотые звёзды */
        }
        .star:hover {
            color: orange;
        }
        .button-container {
            margin: 20px auto;
            text-align: center;
        }
        .btn {
            background-color: transparent;
            color: #ff007f;
            border: 2px solid #ff007f;
            padding: 10px 20px;
            margin: 5px;
            font-size: 1em;
            cursor: pointer;
            transition: 0.3s;
        }
        .btn:hover {
            background-color: #ff007f;
            color: #121212;
        }
        .search-bar {
            margin-bottom: 20px;
        }
        .searchInput {
            padding: 10px;
            font-size: 1em;
            width: 250px;
            border: 2px solid #ff007f;
            background-color: #1e1e1e;
            color: #e0e0e0;
            border-radius: 5px;
        }
    </style>
</head>

<body>
<header>★ Your Recommendations ★</header>

<div class="button-container search-bar">
    <input type="text" id="searchInput" class="searchInput" placeholder="Search for a song...">
    <button class="btn">Search</button>
    <div id="suggestions"></div>
</div>

<table class="song-table" >
    <thead>
    <tr>
        <th>Listen</th>
        <th>Song</th>
        <th>Artist</th>
        <th>Genre</th>
        <th>Added By User:</th>
        <th>★</th>
    </tr>
    </thead>
    <tbody id="songTable">
    <c:forEach var="song" items="${recommendedSongsForUser}">
        <tr>
            <td><a href="${song.link}"> -> </a></td>
            <td>${song.name}</td>
            <td>
                <c:forEach var="authorSong" items="${song.authorSongs}">

                <form action="${pageContext.request.contextPath}/author" method="get">
                    <input type="hidden" name="aut_id" value="${authorSong.author.id}">
                    <input type="submit" value="${authorSong.author.name}" class="btn">
                </form>
            </c:forEach>
                    </td>

            <td>
                <form action="${pageContext.request.contextPath}/genre" method="get">
                    <input type="hidden" name="genre" value="${song.genre}">
                    <input type="submit" value="${song.genre}" class="btn">
                </form></td>
            <td>${song.user.name}</td>
            <td class="star" id="star${song.id}" onclick="likeSong(${song.id})">☆</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="button-container">

    <form action="${pageContext.request.contextPath}/like-song">
        <input type="submit" class="btn" value="Liked Songs">
    </form>
</div>
<div class="button-container">

    <form action="${pageContext.request.contextPath}/add-song">
        <input type="submit" class="btn" value="Add Song">
    </form>
</div>
<div class="button-container">

    <form action="${pageContext.request.contextPath}/home">
        <input type="submit" class="btn" value="Refresh">
    </form>
</div>
<script src="./static/searchScript.js">

</script>
<script src = "./static/likeScript.js">

</script>



</body>
</html>