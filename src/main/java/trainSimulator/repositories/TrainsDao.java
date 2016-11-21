package trainSimulator.repositories;

import trainSimulator.models.Train;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TrainsDao {
    List<Train> findAll();

    Train get(Long id);

    void saveOrUpdate(Train train);

    void delete(Long id);

    Train findOne(Long id);
}
