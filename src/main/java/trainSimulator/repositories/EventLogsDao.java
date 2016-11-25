package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import trainSimulator.models.EventLog;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Repository
public class EventLogsDao extends AbstractJpaDao<EventLog> implements EventLogsDaoInterface {
    public EventLogsDao() {
        super();
        setClazz(EventLog.class);
    }
}
