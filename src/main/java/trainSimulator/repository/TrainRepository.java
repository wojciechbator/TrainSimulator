package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainSimulator.models.Route;
import trainSimulator.models.Station;
import trainSimulator.models.Train;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train findOne(String criteria);

    List<Train> findTrainsOnStation(Station station);

    List<Train> findTrainsOnRoute(Route route);
}
