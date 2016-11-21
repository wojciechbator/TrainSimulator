package trainSimulator.repositories;

import trainSimulator.models.GeneratorParameter;

import java.util.List;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface GeneratorParametersDao {
    List<GeneratorParameter> findAll();

    GeneratorParameter get(Long id);

    void saveOrUpdate(GeneratorParameter generatorParameter);

    void delete(Long id);

    GeneratorParameter findOne(String value);
}