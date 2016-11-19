package annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by mitron-wojtek on 17.11.16.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueUserValidator.class})
public @interface UniqueUsername {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}