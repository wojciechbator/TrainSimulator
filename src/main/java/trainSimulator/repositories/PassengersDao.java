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

}