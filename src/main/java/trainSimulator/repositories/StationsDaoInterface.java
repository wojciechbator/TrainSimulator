package trainSimulator.repositories;

import trainSimulator.models.Station;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */

public interface StationsDaoInterface {
    List<Station> findAll();

    void create(Station station);

    Station update(Station station);

    void delete(Station station);

    Station findOne(long id);

    void deleteById(long entityId);
}
