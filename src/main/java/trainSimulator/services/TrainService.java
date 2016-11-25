package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Train;
import trainSimulator.repositories.TrainsDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class TrainService {
    private final TrainsDaoInterface trainsDaoInterface;

    @Autowired
    public TrainService(TrainsDaoInterface trainsDaoInterface) {
        this.trainsDaoInterface = trainsDaoInterface;
    }

    public void saveTrain(final Train Train) {
        trainsDaoInterface.update(Train);
    }

    public void createTrain(Train train) {
        trainsDaoInterface.create(train);
    }

    public void deleteTrain(final Train train) {
        trainsDaoInterface.delete(train);
    }

    public void deleteTrainById(final long id) {
        trainsDaoInterface.deleteById(id);
    }

    public void findTrain(final long id) {
        trainsDaoInterface.findOne(id);
    }

    public void clearTrains() {
        List<Train> allTrains = trainsDaoInterface.findAll();
        for (Train train : allTrains) {
            deleteTrain(train);
        }
    }

    public List<Train> getAllTrains() {
        return trainsDaoInterface.findAll();
    }

    //TODO: Do more stuff with trains, for example delete from route, IDK
}
