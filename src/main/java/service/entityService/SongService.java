package service.entityService;

import dao.impls.SongDao;
import dto.UserDto;
import entity.Song;
import lombok.AllArgsConstructor;

import java.util.List;
@AllArgsConstructor
public class SongService {
    private SongDao songDao;
    public List<Song> getRecommendedSongsForUser(UserDto user) {
        return songDao.getRecommendedSongsForUserId(user.getId());
    }
    public Song getSongById(Integer id){
        return songDao.findById(id);
    }
}
