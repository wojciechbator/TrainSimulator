package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trainSimulator.models.Route;
import trainSimulator.models.Station;
import trainSimulator.models.Train;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    Train findOne(String criteria);

    List<Train> findTrainsOnStation(Station station);

    List<Train> findTrainsOnRoute(Route route);
}
