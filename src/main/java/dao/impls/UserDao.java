package dao.impls;

import dao.BaseDao;
import entity.User;
import lombok.AllArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@AllArgsConstructor
public class UserDao implements BaseDao<User> {
    private final SessionFactory sessionFactory;
    @Override
    public void save(User o) {
        sessionFactory.inTransaction(
                session -> {
                    try{
                        session.persist(o);
                    } catch (HibernateException e){
                        throw new RuntimeException("Error creating user", e);
                    }
                }
        );
    }

    @Override
    public void update(User o) {
        sessionFactory.inTransaction(
                session -> {
                    try {
                        session.merge(o);
                    } catch (Exception e) {
                        throw new RuntimeException("Error updating user", e);
                    }
                }
        );
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.inTransaction(
                session -> {
                    try {
                        User user = session.get(User.class, id);
                        session.remove(user.getId());
                    } catch (Exception e) {
                        throw new RuntimeException("Can't delete user with id: " + id, e);
                    }
                }
        );
    }

    @Override
    public User findById(Integer id) {
        User user;
        try(Session session = sessionFactory.openSession()) {
            user = session.find(User.class, id);
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find user with id: " + id, e);
        }
        return user;
    }
    public User findByEmail(String email) {
        User user;
        try(Session session = sessionFactory.openSession()) {
            user = (User) session
                    .createQuery("from User where email = :email", User.class)
                    .setParameter("email", email);
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find user with email: " + email, e);
        }
        return user;
    }
}
