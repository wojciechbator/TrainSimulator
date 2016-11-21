package trainSimulator.repositories;

import trainSimulator.models.Passenger;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface PassengersDao {
    List<Passenger> findAll();

    Passenger get(Long id);

    void saveOrUpdate(Passenger passenger);

    void delete(Long id);

    Passenger findOne(Long id);
}
