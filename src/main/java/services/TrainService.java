package services;

import models.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.TrainRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
public class TrainService {
    private final TrainRepository trainRepository;

    @Autowired
    public TrainService(TrainRepository TrainRepository) {
        this.trainRepository = TrainRepository;
    }

    public void saveTrain(Train Train) {
        trainRepository.save(Train);
    }

    public void deleteTrain(Train Train) {
        trainRepository.delete(Train);
    }

    public void findTrain(Integer id) {
        trainRepository.findOne(String.valueOf(id));
    }

    public void clearTrains() {
        List<Train> allTrains = trainRepository.findAll();
        allTrains.forEach(this::deleteTrain);
    }
}
