package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainSimulator.models.Station;
import trainSimulator.repository.StationRepository;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
public class StationService {
    private final StationRepository stationRepository;

    @Autowired
    public StationService(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void saveStation(Station Station) {
        stationRepository.save(Station);
    }

    public void deleteStation(Station Station) {
        stationRepository.delete(Station);
    }

    public void findStation(Integer id) {
        stationRepository.findOne(String.valueOf(id));
    }

    public void clearStations() {
        List<Station> allStations = stationRepository.findAll();
        allStations.forEach(this::deleteStation);
    }
}
