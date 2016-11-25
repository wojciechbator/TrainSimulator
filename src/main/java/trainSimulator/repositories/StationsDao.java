package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.Station;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class StationsDao extends AbstractJpaDao<Station> implements StationsDaoInterface {
    public StationsDao() {
        super();
        setClazz(Station.class);
    }
}
