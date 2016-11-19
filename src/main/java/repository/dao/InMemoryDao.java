package repository.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public class InMemoryDao<E, K> implements GenericDao<E, K> {
    private List<E> entities = new ArrayList<E>();

    public void add(E entity) {
        entities.add(entity);
    }

    public void update(E entity) {
        throw new UnsupportedOperationException("For now not supported!");
    }

    public void remove(E entity) {
        entities.remove(entity);
    }

    public E find(K key) {
        if (entities.isEmpty()) {
            return null;
        }
        return entities.get(0);
    }

    public List<E> list() {
        return entities;
    }
}
