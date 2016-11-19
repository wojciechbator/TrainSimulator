package repository.dao;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface GenericDao<E, K> {
    //some basic operations on database tables
    void add(E entity);

    void update(E entity);

    void remove(E entity);

    E find(K key);

    List<E> list();
}
