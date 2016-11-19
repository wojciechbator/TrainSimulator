package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Passenger;
import trainSimulator.models.Train;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findOne(String criteria);

    List<Passenger> findPassengersInTrain(Train train);
}
