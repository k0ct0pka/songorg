package dao.impls;

import dao.BaseDao;
import entity.Author;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
public class AuthorDao implements BaseDao<Author> {
    private final SessionFactory sessionFactory;
    @Override
    public void save(Author o) {

    }

    @Override
    public void update(Author o) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Author findById(Integer id) {
        return null;
    }


    public Long countListeners(Integer id) {
        AtomicReference<Long> countListeners = new AtomicReference<>();
        sessionFactory.inSession(
                session -> {
                    countListeners.set(session.createQuery("""
                                        select count(distinct us.user.id)
                                        from UserSong us
                                        where us.song.id in (
                                            select aus.song.id
                                            from AuthorSong aus
                                            where aus.author.id = :authorId
                                        )
                                    """, Long.class)
                            .setParameter("authorId", id)
                            .getSingleResult());
                }
        );
        return countListeners.get();
    }
}
