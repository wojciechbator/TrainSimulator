package trainSimulator.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.GeneratorParameter;
import trainSimulator.repositories.GeneratorParametersDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service("generatorParametersService")
@Transactional
public class GeneratorParametersService {
    private static final Logger logger = Logger.getLogger(GeneratorParametersService.class);
    private final GeneratorParametersDaoInterface generatorParametersDao;

    @Autowired
    public GeneratorParametersService(final GeneratorParametersDaoInterface generatorParametersDao) {
        this.generatorParametersDao = generatorParametersDao;
    }

    void saveGeneratorParameter(final GeneratorParameter generatorParameter) {
        generatorParametersDao.update(generatorParameter);
    }

    public List<GeneratorParameter> findAll() {
        return generatorParametersDao.findAll();
    }

    GeneratorParameter findGeneratorParameterById(final long id) {
        logger.info("Found generator parameter with id: " + id);
        return generatorParametersDao.findOne(id);
    }

    public void createParameter(final GeneratorParameter generatorParameter) {
        logger.info("Created parameter with id: " + generatorParameter.getId());
        generatorParametersDao.create(generatorParameter);
    }

    public void deleteGeneratorParameterById(final long id) {
        logger.info("Deleted generator parameter by id with id: " + id);
        generatorParametersDao.deleteById(id);
    }

    private void deleteGeneratorParameter(GeneratorParameter generatorParameter) {
        logger.info("Deleted generator parameter with id: " + generatorParameter.getId());
        generatorParametersDao.delete(generatorParameter);
    }

    void clearParameters() {
        List<GeneratorParameter> generatorParameters = generatorParametersDao.findAll();
        for (GeneratorParameter generatorParameter : generatorParameters) {
            deleteGeneratorParameter(generatorParameter);
        }
        logger.info("Cleared parameters!");
    }

}
