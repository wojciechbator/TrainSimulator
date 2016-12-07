package trainSimulator.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class PassengersDao extends AbstractJpaDao<Passenger> implements PassengersDaoInterface {
    @PersistenceContext
    private EntityManager entityManager;
    public PassengersDao() {
        super();
        setClazz(Passenger.class);
    }

    @Override
    @Cacheable("passenger")
    public Passenger findOne(final long id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    @Cacheable("passengers")
    public List<Passenger> findAll() {
        return entityManager.createQuery("from " + Passenger.class.getName()).getResultList();
    }
}