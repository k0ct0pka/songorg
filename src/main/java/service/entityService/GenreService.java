package service.entityService;

import dao.impls.SongDao;
import entity.Song;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class GenreService {
    private SongDao songDao;
    public void getSongsByGenre(HttpServletRequest request) {
        String genre =  request.getParameter("genre");
        List<Song> songsByGenre = songDao.getSongsByGenre(genre);
        request.setAttribute("songsByGenre", songsByGenre);
        request.setAttribute("genre", genre);
    }
}
