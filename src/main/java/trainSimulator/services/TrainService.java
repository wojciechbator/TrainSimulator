package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Route;
import trainSimulator.models.Station;
import trainSimulator.models.Train;
import trainSimulator.repositories.TrainsDaoInterface;
import trainSimulator.utilities.TrainState;

import java.util.ArrayList;
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

    void saveTrain(final Train Train) {
        trainsDaoInterface.update(Train);
    }

    void moveToNextStation(Train train) {
        if (train.getStation().getId() < train.getRoute().getStationsOnRoute().size() + 1) {
            train.setStation(train.getRoute().getStationsOnRoute().get((int) train.getStation().getId()));
            train.setState(TrainState.PLANNED);
        } else {
            train.setState(TrainState.ENDED);
        }
    }

    void createTrain(final Train train) {
        trainsDaoInterface.create(train);
    }

    void deleteTrain(final Train train) {
        trainsDaoInterface.delete(train);
    }

    void deleteTrainById(final long id) {
        trainsDaoInterface.deleteById(id);
    }

    Train findTrain(final long id) {
        return trainsDaoInterface.findOne(id);
    }

    void clearTrains() {
        List<Train> allTrains = trainsDaoInterface.findAll();
        for (Train train : allTrains) {
            deleteTrain(train);
        }
    }

    public List<Train> getAllTrains() {
        return trainsDaoInterface.findAll();
    }

}
