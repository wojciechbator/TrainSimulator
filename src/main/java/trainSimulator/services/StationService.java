package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Station;
import trainSimulator.models.Train;
import trainSimulator.repositories.StationsDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class StationService {
    private static final Logger logger = Logger.getLogger(StationService.class);
    private final StationsDaoInterface stationsDaoInterface;
    private List<Train> trainsOnStation;

    @Autowired
    public StationService(StationsDaoInterface stationsDaoInterface) {
        this.stationsDaoInterface = stationsDaoInterface;
    }

    public void saveStation(final Station station) {
        stationsDaoInterface.update(station);
        logger.info("Saved station: " + station.getName());
    }

    void deleteStation(final Station station) {
        stationsDaoInterface.delete(station);
        logger.info("Deleted station: " + station.getName());
    }

    public Station getNextStation(Station station) {
        //get method iterates from 0 and first id is 1, so this will actually return next station
        return stationsDaoInterface.findAll().get((int) station.getId());
    }

    public void deleteStationById(final long id) {
        stationsDaoInterface.deleteById(id);
        logger.info("Deleted station with id: " + id);
    }

    public void createStation(final Station station) {
        stationsDaoInterface.create(station);
        logger.info("Created station: " + station.getName());
    }

    void addTrainToStation(Station station, Train train) {
        if(!station.getTrainsOnStation().contains(train))
            trainsOnStation.add(train);
    }

    void removeTrainFromStation(Station station, Train train) {
        if (station.getTrainsOnStation().contains(train))
            trainsOnStation.remove(train);
    }

    public Station findStation(final long id) {
        logger.info("Found station with id: " + id);
        return stationsDaoInterface.findOne(id);
    }

    List<Station> findAllStations() {
        return stationsDaoInterface.findAll();
    }

    public void clearStations() {
        List<Station> allStations = stationsDaoInterface.findAll();
        for (Station station : allStations) {
            deleteStation(station);
        }
        logger.info("Cleared stations!");
    }
}
