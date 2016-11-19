package repository;

import models.Route;
import models.Station;
import models.Train;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train findOne(String criteria);

    List<Train> findTrainsOnStation(Station station);

    List<Train> findTrainsOnRoute(Route route);
}
