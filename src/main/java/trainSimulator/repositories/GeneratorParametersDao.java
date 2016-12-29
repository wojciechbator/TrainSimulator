package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.GeneratorParameter;

import org.springframework.cache.annotation.Cacheable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Transactional
@Repository
public class GeneratorParametersDao extends AbstractJpaDao<GeneratorParameter> implements GeneratorParametersDaoInterface {
    @PersistenceContext
    EntityManager entityManager;

    public GeneratorParametersDao() {
        super();
        setClazz(GeneratorParameter.class);
    }
}