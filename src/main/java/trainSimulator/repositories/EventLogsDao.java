package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.EventLog;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
@Transactional
public class EventLogsDao extends AbstractJpaDao<EventLog> implements EventLogsDaoInterface {
    @PersistenceContext
    EntityManager entityManager;
    public EventLogsDao() {
        super();
        setClazz(EventLog.class);
    }

}
