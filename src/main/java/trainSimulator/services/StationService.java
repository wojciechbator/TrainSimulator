package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Station;
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

    @Autowired
    public StationService(StationsDaoInterface stationsDaoInterface) {
        this.stationsDaoInterface = stationsDaoInterface;
    }

    public void saveStation(final Station station) {
        stationsDaoInterface.update(station);
        logger.info("Saved station: " + station.getName());
    }

    private void deleteStation(final Station station) {
        stationsDaoInterface.delete(station);
        logger.info("Deleted station: " + station.getName());
    }

    public void deleteStationById(final long id) {
        stationsDaoInterface.deleteById(id);
        logger.info("Deleted station with id: " + id);
    }

    public void createStation(final Station station) {
        stationsDaoInterface.create(station);
        logger.info("Created station: " + station.getName());
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
