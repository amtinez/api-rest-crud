package com.amtinez.api.rest.crud.annotations;

import com.amtinez.api.rest.crud.factories.MockUserSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * MockUser is the interface used to implement a mock of the logged-in user.
 *
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = MockUserSecurityContextFactory.class)
public @interface MockUser {

    String username() default "mockFirstName";

    String name() default "mockFirstName mockLastName";

}
