package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.EventLog;
import trainSimulator.repositories.EventLogsDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class EventLogsDaoImplementation implements EventLogsDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public EventLogsDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<EventLog> findAll() {
        @SuppressWarnings("unchecked")
        List<EventLog> allEventLogs = (List<EventLog>) sessionFactory.getCurrentSession()
                .createCriteria(EventLog.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return allEventLogs;
    }

    @Override
    @Transactional
    public EventLog get(Long id) {
        String hql = "from event_register where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<EventLog> eventLogs = (List<EventLog>) query.list();

        if (eventLogs != null && !eventLogs.isEmpty()) {
            return eventLogs.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(EventLog eventLog) {
        sessionFactory.getCurrentSession().saveOrUpdate(eventLog);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        EventLog eventLogToDelete = new EventLog();
        eventLogToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(eventLogToDelete);
    }

    @Override
    @Transactional
    public EventLog findOne(Long id) {
        return sessionFactory.getCurrentSession().get(EventLog.class, id);
    }
}
