package dao.impls;

import dao.BaseDao;
import entity.Author;
import entity.Song;
import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.concurrent.atomic.AtomicReference;
import java.util.*;

@AllArgsConstructor
public class AuthorDao implements BaseDao<Author> {
    private final SessionFactory sessionFactory;
    @Override
    public void save(Author o) {
        sessionFactory.inTransaction(
                session -> {
                    try{
                        session.persist(o);
                    } catch (HibernateException e){
                        throw new RuntimeException("Error creating author", e);
                    }
                }
        );
    }

    @Override
    public void update(Author o) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public Author findById(Integer id) {
        Author author;
        try(Session session = sessionFactory.openSession()) {
            author = session.find(Author.class, id);
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find author with id: " + id, e);
        }
        return author;
    }
    public List<Author> findAll() {
        try(Session session = sessionFactory.openSession()) {
            return session.createQuery("from Author",Author.class).getResultList();
        }
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

    public List<Author> getByName(String query) {
        List<Author> authors;
        try(Session session = sessionFactory.openSession()) {
            authors = session.createQuery("from Author a where LOWER(a.name) LIKE LOWER(CONCAT('%', CONCAT(:q, '%')))",Author.class)
                    .setMaxResults(5)
                    .setParameter("q",query)
                    .getResultList();
        } catch (HibernateException e) {
            authors = Collections.emptyList();
        }
        return authors;
    }
}
