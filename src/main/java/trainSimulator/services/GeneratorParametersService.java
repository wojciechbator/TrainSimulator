package trainSimulator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import trainSimulator.models.GeneratorParameter;
import trainSimulator.repository.GeneratorParametersRepository;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
@Service
public class GeneratorParametersService {
    private final GeneratorParametersRepository generatorParametersRepository;

    @Autowired
    public GeneratorParametersService(GeneratorParametersRepository generatorParametersRepository) {
        this.generatorParametersRepository = generatorParametersRepository;
    }

    public void saveGeneratorParameters(GeneratorParameter generatorParameter) {
        generatorParametersRepository.save(generatorParameter);
    }

    public void deleteGeneratorParameters(GeneratorParameter generatorParameter) {
        generatorParametersRepository.delete(generatorParameter);
    }

    public GeneratorParameter findGeneratorParameters(String parameterName) {
        return generatorParametersRepository.findOne(parameterName);
    }

}
