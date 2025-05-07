package service.entityService;

import constants.Session;
import dao.impls.SongDao;
import dto.UserDto;
import entity.Author;
import entity.Song;
import entity.User;
import entity.embeddableIds.AuthorSongId;
import entity.many_to_many_tables.AuthorSong;
import factory.SongFactory;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mapper.UserMapper;

import java.util.ArrayList;
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
    private SongFactory songFactory;
    private AuthorService authorService;
    private UserMapper userMapper;
    public List<Song> getRecommendedSongsForUser(UserDto user) {
        return songDao.getRecommendedSongsForUserId(user.getId());
    }
    public Song getSongById(Integer id){
        return songDao.findById(id);
    }

    public void addSong(HttpServletRequest req) {
        Map<String, Object> songParams = getSongParams(req);
        Song song = songFactory.create(songParams);

        List<AuthorSong> authorSongs = new ArrayList<>();
        for(String id : (String[]) songParams.get(AUTHOR_IDS)){
            Author author = authorService.getAuthor(Integer.valueOf(id));
            AuthorSong authorSong = new AuthorSong();
            authorSong.setAuthorSongId(
                    AuthorSongId.builder()
                            .authorId(author.getId()).build()
            );
            authorSong.setSong(song);
            authorSong.setAuthor(author);
            authorSongs.add(authorSong);
        }
        song.setAuthorSongs(authorSongs);
        UserDto userDto = (UserDto) req.getSession().getAttribute(Session.USER.getName());
        song.setUser(userMapper.toEntity(userDto));
        songDao.save(song);
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
