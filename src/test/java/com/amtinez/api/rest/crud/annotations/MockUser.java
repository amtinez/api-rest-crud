package com.amtinez.api.rest.crud.annotations;

import com.amtinez.api.rest.crud.factories.MockUserSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author amartinezcerro@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = MockUserSecurityContextFactory.class)
public @interface MockUser {

    String username() default "mockFirstName";

    String name() default "mockFirstName mockLastName";

}
