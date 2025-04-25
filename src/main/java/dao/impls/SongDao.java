package dao.impls;

import dao.BaseDao;
import entity.Song;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;
@AllArgsConstructor
public class SongDao implements BaseDao<Song> {
    private final SessionFactory sessionFactory;
    @Override
    public void save(Song o) {

    }

    @Override
    public void update(Song o) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Song findById(Integer id) {
        return null;
    }
    public List<Song> getRecommendedSongsForUserId(Integer userId){
        sessionFactory.inTransaction(
                session -> {
                    session.createQuery("from Song s left join AuthorSong autS on s.id = autS.authorSongId.songId left join  Author a on a.id = autS.authorSongId.authorId left join User u on u.id = );
                }
        );
    }
}
