package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.Train;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class TrainsDao extends AbstractJpaDao<Train> implements TrainsDaoInterface {
    public TrainsDao() {
        super();
        setClazz(Train.class);
    }
}
