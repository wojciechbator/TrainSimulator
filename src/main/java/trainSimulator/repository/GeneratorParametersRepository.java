package trainSimulator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import trainSimulator.models.GeneratorParameter;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface GeneratorParametersRepository extends JpaRepository<GeneratorParameter, Integer> {
    GeneratorParameter findOne(String criteria);
}
