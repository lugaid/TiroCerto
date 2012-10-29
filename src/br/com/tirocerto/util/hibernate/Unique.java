package br.com.tirocerto.util.hibernate;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueValidator.class)
@Documented
public @interface Unique {
    String message() default "unique_";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * The mapped hibernate/jpa entity class
     */
    Class<?> entity();

    /**
     * The property of the entity we want to validate for uniqueness. Default name is "id"
     */
    String property() default "id";
}
