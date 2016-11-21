package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Train;
import trainSimulator.repositories.implementations.TrainsDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class TrainService {
    private final TrainsDaoImplementation trainsDaoImplementation;

    @Autowired
    public TrainService(TrainsDaoImplementation trainsDaoImplementation) {
        this.trainsDaoImplementation = trainsDaoImplementation;
    }

    public void saveTrain(Train Train) {
        trainsDaoImplementation.saveOrUpdate(Train);
    }

    public void deleteTrain(Long id) {
        trainsDaoImplementation.delete(id);
    }

    public void findTrain(Long id) {
        trainsDaoImplementation.findOne(id);
    }

    public void clearTrains() {
        List<Train> allTrains = trainsDaoImplementation.findAll();
        for (Train train : allTrains) {
            deleteTrain(train.getId());
        }
    }

    public List<Train> getAllTrains() {
        return trainsDaoImplementation.findAll();
    }

    //TODO: Do more stuff with trains, for example delete from route, IDK
}
