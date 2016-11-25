package trainSimulator.services;

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
    private final StationsDaoInterface stationsDaoInterface;

    @Autowired
    public StationService(StationsDaoInterface stationsDaoInterface) {
        this.stationsDaoInterface = stationsDaoInterface;
    }

    public void saveStation(final Station Station) {
        stationsDaoInterface.update(Station);
    }

    public void deleteStation(final Station station) {
        stationsDaoInterface.delete(station);
    }

    public void deleteStationById(final long id) {
        stationsDaoInterface.deleteById(id);
    }

    public void createStation(final Station station) {
        stationsDaoInterface.create(station);
    }

    public void findStation(final long id) {
        stationsDaoInterface.findOne(id);
    }

    public List<Station> findAllStations() {
        return stationsDaoInterface.findAll();
    }

    public void clearStations() {
        List<Station> allStations = stationsDaoInterface.findAll();
        for (Station station : allStations) {
            deleteStation(station);
        }
    }
}
