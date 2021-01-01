package dao;

public interface AbstractDAO<T> {

    void save(T t);

    void update(T t);

    void delete(T t);

}
