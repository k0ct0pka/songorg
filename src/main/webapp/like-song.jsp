<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Liked Songs</title>
    <link rel="stylesheet" href="./static/style.css">
</head>
<body>
<div class="container">
<h1 class="title">My Liked Songs</h1>

<table class="songs-table">
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
    <tbody>
    <c:forEach var="userSong" items="${user.userSongs}">
        <tr>
            <td><a href="${userSong.song.link}" class="listen-link" target="_blank">Слушать</a></td>
            <td>${userSong.song.name}</td>


            <td>
                <c:forEach var="authorSong" items="${userSong.song.authorSongs}">

                    <form action="${pageContext.request.contextPath}/author" method="get">
                        <input type="hidden" name="aut_id" value="${authorSong.author.id}">
                        <input type="submit" value="${authorSong.author.name}">
                    </form>
                </c:forEach>
            </td>

            <td>
                <form action="${pageContext.request.contextPath}/genre" method="get">
                    <input type="hidden" name="genre" value="${userSong.song.genre}">
                    <input type="submit" value="${userSong.song.genre}">
                </form></td>
            <td>${userSong.song.user.name}</td>
            <td class="star" id="star${userSong.song.id}" onclick="likeSong(${userSong.song.id})">★</td>

        </tr>
    </c:forEach>
    </tbody>
</table>


    <div class="button-container">

        <form action="${pageContext.request.contextPath}/home">
            <input type="submit" class="btn" value="Return">
        </form>
    </div>
</div>
<script src="static/likeScript.js"></script>
</body>
</html>