package dao;

public interface BaseDao<T> {
    void save(T o);
    void update(T o);
    void delete(Integer id);
    T findById(Integer id);
}
