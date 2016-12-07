package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.GeneratorParameter;
import trainSimulator.repositories.GeneratorParametersDaoInterface;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class GeneratorParametersService {
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
        return generatorParametersDao.findOne(id);
    }

    public void createParameter(final GeneratorParameter generatorParameter) {
        generatorParametersDao.create(generatorParameter);
    }

    public void deleteGeneratorParameterById(final long id) {
        generatorParametersDao.deleteById(id);
    }

    private void deleteGeneratorParameter(GeneratorParameter generatorParameter) {
        generatorParametersDao.delete(generatorParameter);
    }

    void clearParameters() {
        List<GeneratorParameter> generatorParameters = generatorParametersDao.findAll();
        for (GeneratorParameter generatorParameter : generatorParameters) {
            deleteGeneratorParameter(generatorParameter);
        }
    }

}
