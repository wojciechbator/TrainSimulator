package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import trainSimulator.models.GeneratorParameter;
import trainSimulator.repositories.GeneratorParametersDao;
import trainSimulator.repositories.implementations.GeneratorParametersDaoImplementation;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
@Transactional
public class GeneratorParametersService {
    private final GeneratorParametersDao generatorParametersDao;

    @Autowired
    public GeneratorParametersService(GeneratorParametersDaoImplementation eventLogsDaoImplementation) {
        this.generatorParametersDao = eventLogsDaoImplementation;
    }

    void saveGeneratorParameter(GeneratorParameter generatorParameter) {
        generatorParametersDao.saveOrUpdate(generatorParameter);
    }

    public GeneratorParameter findGeneratorParameter(String value) {
        return generatorParametersDao.findOne(value);
    }

    private void deleteGeneratorParameter(Long id) {
        generatorParametersDao.delete(id);
    }

    void clearParameters() {
        List<GeneratorParameter> generatorParameters = generatorParametersDao.findAll();
        for (GeneratorParameter generatorParameter : generatorParameters) {
            deleteGeneratorParameter(generatorParameter.getId());
        }
    }

}
