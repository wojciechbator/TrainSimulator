package trainSimulator.repositories;

import trainSimulator.models.Station;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */

public interface StationsDao {
    List<Station> findAll();

    Station get(Long id);

    void saveOrUpdate(Station station);

    void delete(Long id);

    Station findOne(Long id);
}
