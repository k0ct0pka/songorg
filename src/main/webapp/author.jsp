<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">
<head>
    <title>Author's Songs</title>
    <style>
        body {
            background-color: #121212;
            color: #e0e0e0;
            font-family: 'Courier New', Courier, monospace;
            margin: 0;
            padding: 0;
        }
        header {
            text-align: center;
            padding: 20px;
            font-size: 2em;
            color: #ff007f;
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
            color: #FFD700;
        }
        .star:hover {
            color: orange;
        }
        .btn {
            background-color: transparent;
            color: #ff007f;
            border: 2px solid #ff007f;
            padding: 6px 14px;
            margin: 2px;
            font-size: 0.9em;
            cursor: pointer;
            transition: 0.3s;
        }
        .btn:hover {
            background-color: #ff007f;
            color: #121212;
        }
    </style>
</head>

<body>
<div style="margin: 30px auto; width: 50%; background-color: #1e1e1e; padding: 20px; border-radius: 10px; border: 2px solid #ff007f; text-align: center;">
    <h2 style="color: #ff007f; margin-bottom: 10px;">★ About the Author ★</h2>
    <p><strong>Name:</strong> ${author.name}</p>
    <p><strong>Concerts:</strong>
        <a href="${author.concertsLink}" target="_blank" style="color: #00ffff;">
            ${author.concertsLink}
        </a>
    </p>
    <p><strong>Number Listeners:</strong> ${countListeners}</p>
</div>
<header>★ Songs by Author ★</header>

<table class="song-table">
    <thead>
    <tr>
        <th>Listen</th>
        <th>Song</th>
        <th>Artist</th>
        <th>Genre</th>
        <th>Added By</th>
        <th>★</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="as" items="${author.authorSongs}">

        <tr>
            <td><a href="${as.song.link}"> -> </a></td>
            <td>${as.song.name}</td>
            <td>
                <c:forEach var="authorSong" items="${as.song.authorSongs}">
                    <form action="${pageContext.request.contextPath}/author" method="get" style="display:inline;">
                        <input type="hidden" name="aut_id" value="${authorSong.author.id}">
                        <input type="submit" value="${authorSong.author.name}" class="btn">
                    </form>
                </c:forEach>
            </td>
            <td>
                <form action="${pageContext.request.contextPath}/genre" method="get">
                    <input type="hidden" name="genre" value="${as.song.genre}">
                    <input type="submit" value="${as.song.genre}" class="btn">
                </form>
            </td>
            <td>${as.song.user.name}</td>
            <td class="star" id="star${as.song.id}" onclick="likeSong(${as.song.id})">☆</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="button-container">

    <form action="${pageContext.request.contextPath}/home">
        <input type="submit" class="btn" value="Return">
    </form>
</div>

<script src="./static/likeScript.js"></script>
</body>
</html>