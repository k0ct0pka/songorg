package service.entityService;

import dao.impls.SongDao;
import dto.UserDto;
import entity.Song;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class SongService {
    public static final String NAME = "name";
    public static final String LINK = "link";
    public static final String GENRE = "genre";
    public static final String AUTHOR_IDS = "authorIds";
    private SongDao songDao;
    public List<Song> getRecommendedSongsForUser(UserDto user) {
        return songDao.getRecommendedSongsForUserId(user.getId());
    }
    public Song getSongById(Integer id){
        return songDao.findById(id);
    }

    public void addSong(HttpServletRequest req) {

    }
    private Map<String,Object> getSongParams(HttpServletRequest request) {
        Map<String,Object> params = new HashMap<>();
        params.put(NAME,request.getParameter(NAME));
        params.put(LINK,request.getParameter(LINK));
        params.put(GENRE,request.getParameter(GENRE));
        params.put(AUTHOR_IDS,request.getParameterValues(AUTHOR_IDS));
        return params;
    }
}
