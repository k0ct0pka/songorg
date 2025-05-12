package service;

import constants.Session;
import dto.UserDto;
import entity.Author;
import entity.Song;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import service.entityService.AuthorService;
import service.entityService.SongService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class HomeService {
    private SongService songService;
    private AuthorService authorService;
    public void initiateHomePage(HttpServletRequest req){
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto)session.getAttribute(Session.USER.getName());
        List<Song> recommendedSongsForUser = songService.getRecommendedSongsForUser(userDto);
        session.setAttribute(Session.RECOMMENDED_SONGS_FOR_USER.getName(), recommendedSongsForUser);
    }
    public Map<String,Object> search(HttpServletRequest req){
        String query = req.getParameter("query");
        List<Song> songByName = songService.getSongByName(query);
        List<Author> authors = authorService.getAuthorsByName(query);
        Map<String,Object> suggestions = new HashMap<>();
        suggestions.put("songs", songByName);
        suggestions.put("authors", authors);
        return suggestions;
    }
}
