package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.TimetableEntity;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class TimetableEntitiesDao extends AbstractJpaDao<TimetableEntity> implements TimetableEntitiesDaoInterface {
    public TimetableEntitiesDao() {
        super();
        setClazz(TimetableEntity.class);
    }
}
