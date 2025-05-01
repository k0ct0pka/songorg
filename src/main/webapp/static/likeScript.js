function likeSong(songId) {
    const params = new URLSearchParams();
    params.append('songId', songId);

    fetch('/like-song', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: params
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Ошибка при лайке песни');
            }
        })
        .then(data => {
            console.log('Песня лайкнута!', data);
            const star = document.getElementById(`star` + songId);

            star.innerText = star.innerText === "★" ? "☆" : "★";

        })
        .catch(error => {
            console.error('Ошибка:', error);
        });
}