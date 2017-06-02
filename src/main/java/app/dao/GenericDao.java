package app.dao;


import java.io.Serializable;
import java.util.List;

public interface GenericDao<PK extends Serializable, T> {
    void saveEntity(T entity);

    T getByKey(PK id);

    List<T> getAll();

    void update(T entity);

    void deleteByKey(PK id);
}
