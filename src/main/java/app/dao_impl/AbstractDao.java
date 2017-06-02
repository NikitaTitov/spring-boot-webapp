package app.dao_impl;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
public abstract class AbstractDao<PK extends Serializable, T> {

    @PersistenceContext
    EntityManager entityManager;

    private final Class<T> gClass;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.gClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @SuppressWarnings("unchecked")
    public T getByKey(PK key) {
        return entityManager.find(gClass, key);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public List<T> getAll() {
        String genericClassName = gClass.toGenericString();
        genericClassName = genericClassName.substring(genericClassName.lastIndexOf('.') + 1);
        String hql = "FROM " + genericClassName;
        return entityManager.createQuery(hql).getResultList();
    }

    public void saveEntity(T entity) {
        entityManager.persist(entity);
    }

    public void deleteByKey(PK key) {
        T entity = entityManager.find(gClass, key);
        entityManager.remove(entity);
    }
}
