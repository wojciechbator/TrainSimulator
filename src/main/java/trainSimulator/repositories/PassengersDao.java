package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class PassengersDao extends AbstractJpaDao<Passenger> implements PassengersDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public PassengersDao() {
        super();
        setClazz(Passenger.class);
    }

}