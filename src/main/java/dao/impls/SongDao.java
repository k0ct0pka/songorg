package dao.impls;

import dao.BaseDao;
import entity.Song;
import entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SongDao implements BaseDao<Song> {
    private final SessionFactory sessionFactory;
    @Override
    public void save(Song o) {
        sessionFactory.inTransaction(
                session -> {
                    try{
                        session.merge(o);
                    } catch (HibernateException e){
                        throw new RuntimeException("Error creating user", e);
                    }
                }
        );
    }

    @Override
    public void update(Song o) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Song findById(Integer id) {
        Song song;
        try(Session session = sessionFactory.openSession()) {
            song = session.find(Song.class, id);
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find song with id: " + id, e);
        }
        return song;
    }
    public List<Song> getRecommendedSongsForUserId(Integer userId){
        AtomicReference<List<Song>> recommendedSongs = new AtomicReference<>(new ArrayList<>());
        sessionFactory.inTransaction(
                session -> {
                    Query<Song> query = session.createQuery("select distinct s from AuthorSong authSong join authSong.song s" +
                            " where authSong.author.id in (select auS.author.id from UserSong usS" +
                            " join usS.song.authorSongs auS where usS.user.id = :userId ) " +
                            " and s.id not in (select usS.song.id from UserSong usS where usS.user.id = :userId)", Song.class);
                    query.setMaxResults(100);

                    query.setParameter("userId", userId);
                    List<Song> resultList = query.getResultList();
                    if(resultList.size() < 10){
                        List<Integer> ids = resultList.stream().map(Song::getId).toList();
                        List<Song> songs = session.createQuery("from Song s where s.id not in (select usS.song.id from UserSong usS where usS.user.id = :userId) and s.id not in :existingIds", Song.class)
                                .setMaxResults(50)
                                .setParameter("userId", userId)
                                .setParameter("existingIds", ids)
                                .getResultList();
                        songs.addAll(resultList);
                        Collections.shuffle(songs);
                        recommendedSongs.set(songs);
                        return;
                    }
                    recommendedSongs.set(resultList);
                }
        );
        return recommendedSongs.get();
    }
    public List<Song> getSongsByGenre(String genre){
        List<Song> songs;
        try(Session session = sessionFactory.openSession()) {
            songs = session.createQuery("from Song s where lower(s.genre) = :genre",Song.class)
                    .setParameter("genre", genre.toLowerCase())
                    .getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find song with genre: " + genre, e);
        }
        return songs;
    }

    public List<Song> getSongsByName(String query) {
        List<Song> songs;
        try(Session session = sessionFactory.openSession()) {
            songs = session.createQuery("from Song s where LOWER(s.name) LIKE LOWER(CONCAT('%', CONCAT(:q, '%')))",Song.class)
                    .setMaxResults(5)
                    .setParameter("q",query)
                    .getResultList();
        } catch (HibernateException e) {
            songs = Collections.emptyList();
        }
        return songs;
    }
}
