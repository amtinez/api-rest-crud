package com.amtinez.api.rest.crud.annotations;

import com.amtinez.api.rest.crud.validators.UniqueUserEmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * UniqueUserEmail is the interface used to implement the uniqueness restriction of a user's email address.
 *
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueUserEmailValidator.class)
public @interface UniqueUserEmail {

    String message() default "an account is already registered with this email address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
