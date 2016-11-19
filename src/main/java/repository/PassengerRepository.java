package repository;

import models.Passenger;
import models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {
    Passenger findOne(String criteria);

    List<Passenger> findPassengersInTrain(Train train);
}
