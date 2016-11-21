package trainSimulator.repositories.implementations;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.GeneratorParameter;
import trainSimulator.repositories.GeneratorParametersDao;

import java.util.List;

/**
 * Created by mitron-wojtek on 20.11.16.
 */
@Repository
public class GeneratorParametersDaoImplementation implements GeneratorParametersDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public GeneratorParametersDaoImplementation(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public List<GeneratorParameter> findAll() {
        @SuppressWarnings("unchecked")
        List<GeneratorParameter> allEventLogs = (List<GeneratorParameter>) sessionFactory.getCurrentSession()
                .createCriteria(GeneratorParameter.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return allEventLogs;
    }

    @Override
    @Transactional
    public GeneratorParameter get(Long id) {
        String hql = "from generator_parameters where id=" + id;
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        @SuppressWarnings("unchecked")
        List<GeneratorParameter> generatorParameters = (List<GeneratorParameter>) query.list();

        if (generatorParameters != null && !generatorParameters.isEmpty()) {
            return generatorParameters.get(0);
        }

        return null;
    }

    @Override
    @Transactional
    public void saveOrUpdate(GeneratorParameter eventLog) {
        sessionFactory.getCurrentSession().saveOrUpdate(eventLog);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        GeneratorParameter generatorParameterToDelete = new GeneratorParameter();
        generatorParameterToDelete.setId(id);
        sessionFactory.getCurrentSession().delete(generatorParameterToDelete);
    }

    @Override
    @Transactional
    public GeneratorParameter findOne(String value) {
        return sessionFactory.getCurrentSession().get(GeneratorParameter.class, value);
    }
}
