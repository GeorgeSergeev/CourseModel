package crud;

/**
 * Интерфейс CRUD операций
 * @param <T> тип ID
 * @param <C> тип обхекта
 */
public interface IService<T,C> {
    void create(C object);
    C load(T id);
    void update(C object);
    void delete(T id);
}
