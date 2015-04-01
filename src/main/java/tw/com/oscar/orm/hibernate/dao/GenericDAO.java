/**
 * Title: Acer Internal Project
 * Copyright: (c) 2015, Acer Inc.
 * Name: GenericDAO
 *
 * @author Oscar
 * @since 2015/4/1
 * <p>
 * H i s t o r y
 * <p>
 * 2015/4/1 Oscar v1
 * + File created
 */
package tw.com.oscar.orm.hibernate.dao;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import tw.com.oscar.orm.hibernate.util.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * <strong>Description:</strong><br>
 * This function include: - TODO <br>
 *
 * @author Oscar
 * @version v1, 2015/4/1
 * @since 2015/4/1
 */
public abstract class GenericDAO<T, PK extends Serializable> implements IGenericDAO<T, PK> {

    private Class<T> persistentClass;
    private Session session;

    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session s) {
        this.session = s;
    }

    protected Session getSession() {
        if (session == null)
            session = HibernateUtil.getSessionFactory().getCurrentSession();
        return session;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    @Override
    public Optional<T> getByPid(PK pk, boolean lock) {
        T entity;
        if (lock) {
            entity = (T) getSession().load(getPersistentClass(), pk, LockOptions.UPGRADE);
        } else {
            entity = (T) getSession().load(getPersistentClass(), pk);
        }

        return Optional.ofNullable(entity);
    }

    @Override
    public Stream<T> findAll() {
        return findByCriteria().stream();
    }

    @Override
    public T save(T entity) {
        getSession().save(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        getSession().update(entity);
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
    }

    @Override
    public void flush() {
        getSession().flush();
    }

    @Override
    public void clean() {
        getSession().clear();
    }

    protected List findByCriteria(Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(getPersistentClass());
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria.list();
    }
}
