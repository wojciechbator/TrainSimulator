package annotations;

import org.springframework.beans.factory.annotation.Autowired;
import repository.PassengerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
public class UniqueUserValidator implements ConstraintValidator<UniqueUsername, String> {
    private final PassengerRepository passengerRepository;

    @Autowired
    public UniqueUserValidator(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public void initialize(UniqueUsername uniqueUsername) {

    }

    public boolean isValid(String criteria, ConstraintValidatorContext constraintValidatorContext) {
        return passengerRepository == null || passengerRepository.findOne(criteria) == null;
    }
}
