package tw.com.oscar.orm.hibernate.dao;

import java.io.Serializable;
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
public interface IGenericDAO<T, PK extends Serializable> {

    Optional<T> getByPid(PK pk, boolean lock);

    Stream<T> findAll();

    T save(T entity);

    void update(T entity);

    void delete(T entity);

    void flush();

    void clean();

}
