package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.Passenger;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class PassengersDao extends AbstractJpaDao<Passenger> implements PassengersDaoInterface {
    public PassengersDao() {
        super();
        setClazz(Passenger.class);
    }
}