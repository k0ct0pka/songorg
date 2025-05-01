package service;

import constants.Session;
import dto.UserDto;
import entity.Song;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import service.entityService.SongService;

import java.util.List;

@AllArgsConstructor
public class HomeService {
    private SongService songService;
    public void initiateHomePage(HttpServletRequest req){
        HttpSession session = req.getSession();
        UserDto userDto = (UserDto)session.getAttribute(Session.USER.getName());
        List<Song> recommendedSongsForUser = songService.getRecommendedSongsForUser(userDto);
        session.setAttribute(Session.RECOMMENDED_SONGS_FOR_USER.getName(), recommendedSongsForUser);
    }
}
