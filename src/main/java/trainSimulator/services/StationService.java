package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Station;
import trainSimulator.repositories.StationsDao;
import trainSimulator.repositories.implementations.StationsDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class StationService {
    private final StationsDao stationsDao;

    @Autowired
    public StationService(StationsDaoImplementation stationsDao) {
        this.stationsDao = stationsDao;
    }

    public void saveStation(Station Station) {
        stationsDao.saveOrUpdate(Station);
    }

    public void deleteStation(Long id) {
        stationsDao.delete(id);
    }

    public void findStation(Long id) {
        stationsDao.findOne(id);
    }

    public void clearStations() {
        List<Station> allStations = stationsDao.findAll();
        for (Station station : allStations) {
            deleteStation(station.getId());
        }
    }
}
