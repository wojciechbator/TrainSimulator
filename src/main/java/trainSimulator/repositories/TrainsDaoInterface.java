package trainSimulator.repositories;

import trainSimulator.models.Train;

import java.util.List;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public interface TrainsDaoInterface {
    List<Train> findAll();

    void create(Train train);

    Train update(Train train);

    void delete(Train train);

    Train findOne(long id);

    void deleteById(long entityId);
}
