let timer = null;

document.getElementById("searchInput").addEventListener("input", function () {
    const query = this.value.trim();
    console.log(query)
    // Отменяем предыдущий запрос, если юзер продолжает печатать
    clearTimeout(timer);

    // Не ищем, если пусто
    if (query.length === 0) {
        document.getElementById("suggestions").style.display = "none";
        return;
    }
    const params = new URLSearchParams();
    params.append('query', query);



    // Задержка перед запросом (debounce)
    timer = setTimeout(() => {
        fetch(`/home`
            ,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: params
            })
            .then(response => response.json())
            .then(data => showSuggestions(data))
            .catch(err => console.error("Ошибка поиска:", err));
    }, 300); // 300ms задержка
});

function showSuggestions(data) {
    const container = document.getElementById("suggestions");
    container.innerHTML = "";

    const { authors, songs } = data;

    if ((authors.length === 0) && (songs.length === 0)) {
        container.style.display = "none";
        return;
    }

    if (authors.length > 0) {
        const authorTitle = document.createElement("div");
        authorTitle.textContent = "Авторы:";
        authorTitle.style.fontWeight = "bold";
        authorTitle.style.padding = "5px";
        container.appendChild(authorTitle);

        authors.forEach(author => {
            const div = document.createElement("div");
            div.textContent = "👤 "+author.name;
            div.style.padding = "5px";
            div.style.cursor = "pointer";
            div.onclick = () => {
                document.getElementById("searchInput").value = author.name;
                container.style.display = "none";
                let params = new URLSearchParams();
                params.append('aut_id', author.id);
                let id = author.id;
                fetch(`/author?aut_id=` + id)
                window.location.href = `/author?aut_id=` + id;
            }
            container.appendChild(div);
        });
    }

    if (songs.length > 0) {
        const songTitle = document.createElement("div");
        songTitle.textContent = "Песни:";
        songTitle.style.fontWeight = "bold";
        songTitle.style.padding = "5px";
        container.appendChild(songTitle);

        songs.forEach(song => {
            const div = document.createElement("div");
            div.textContent = "🎵 "+song.name;
            div.style.padding = "5px";
            div.style.cursor = "pointer";
            div.onclick = () => {
                document.getElementById("searchInput").value = song.name;
                container.style.display = "none";
                displaySearchedSong(song)
            };
            container.appendChild(div);
        });
    }

    container.style.display = "block";
}
function displaySearchedSong(song) {
    const tbody = document.getElementById("songTable");
    tbody.innerHTML = ""; // очищаем таблицу

    let authorForms = "";
    song.authorSongs.forEach(authorS => {
        authorForms += `
            <form action="/author" method="get">
                <input type="hidden" name="aut_id" value="${authorS.author.id}">
                <input type="submit" value="${authorS.author.name}" class="btn">
            </form>`;
    });

    const genreForm = `
        <form action="/genre" method="get">
            <input type="hidden" name="genre" value="${song.genre}">
            <input type="submit" value="${song.genre}" class="btn">
        </form>`;
    const row = `
        <tr>
            <td><a href="${song.link}" target="_blank">→</a></td>
            <td>${song.name}</td>
            <td>${authorForms}</td>
            <td>${genreForm}</td>
            <td></td>
            <td class="star" id="star${song.id}" onclick="likeSong(${song.id})">☆</td>
        </tr>
    `;
    tbody.innerHTML = row;
}