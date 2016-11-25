package trainSimulator.repositories;

import trainSimulator.models.Passenger;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface PassengersDaoInterface {
    List<Passenger> findAll();

    void create(Passenger passenger);

    Passenger update(Passenger passenger);

    void delete(Passenger passenger);

    Passenger findOne(long id);

    void deleteById(long entityId);
}
