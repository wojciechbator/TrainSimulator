package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.Route;
import trainSimulator.repositories.RoutesDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class RoutesDaoImplementation implements RoutesDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public RoutesDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<Route> findAll() {
        @SuppressWarnings("unchecked")
        List<Route> routes = (List<Route>) sessionFactory.getCurrentSession()
                .createCriteria(Route.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return routes;
    }

    @Override
    @Transactional
    public Route get(Long id) {
        String hql = "from routes where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<Route> routes = (List<Route>) query.list();

        if (routes != null && !routes.isEmpty()) {
            return routes.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(Route route) {
        sessionFactory.getCurrentSession().saveOrUpdate(route);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Route routeToDelete = new Route();
        routeToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(routeToDelete);
    }

    @Override
    @Transactional
    public Route findOne(Long id) {
        return sessionFactory.getCurrentSession().get(Route.class, id);
    }
}
