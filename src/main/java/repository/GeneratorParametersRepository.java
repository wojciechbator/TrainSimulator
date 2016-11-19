package repository;

import models.GeneratorParameter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mitron-wojtek on 18.11.16.
 */
public interface GeneratorParametersRepository extends JpaRepository<GeneratorParameter, Integer> {
    GeneratorParameter findOne(String criteria);
}
