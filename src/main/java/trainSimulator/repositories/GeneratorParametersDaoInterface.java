package trainSimulator.repositories;

import trainSimulator.models.GeneratorParameter;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface GeneratorParametersDaoInterface {
    List<GeneratorParameter> findAll();

    void create(GeneratorParameter generatorParameter);

    GeneratorParameter update(GeneratorParameter generatorParameter);

    void delete(GeneratorParameter generatorParameter);

    GeneratorParameter findOne(long id);

    void deleteById(long entityId);
}