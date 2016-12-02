package trainSimulator.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.GeneratorParameter;

/**
 * Created by mitron-wojtek on 25.11.16.
 */
@Transactional
@Repository
public class GeneratorParametersDao extends AbstractJpaDao<GeneratorParameter> implements GeneratorParametersDaoInterface {

    public GeneratorParametersDao() {
        super();
        setClazz(GeneratorParameter.class);
    }
}