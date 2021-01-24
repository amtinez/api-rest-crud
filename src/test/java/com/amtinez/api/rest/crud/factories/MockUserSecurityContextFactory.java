package com.amtinez.api.rest.crud.factories;

import com.amtinez.api.rest.crud.annotations.MockUser;
import com.amtinez.api.rest.crud.models.UserModel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

/**
 * @author amartinezcerro@gmail.com
 */
public class MockUserSecurityContextFactory implements WithSecurityContextFactory<MockUser> {

    private static final String MOCK_USER_FIRST_NAME = "mockFirstName";
    private static final String MOCK_USER_LAST_NAME = "mockLastName";

    @Override
    public SecurityContext createSecurityContext(MockUser mockUser) {
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        final UserModel userModel = UserModel.builder()
                                             .firstName(MOCK_USER_FIRST_NAME)
                                             .lastName(MOCK_USER_LAST_NAME)
                                             .build();
        final Authentication authentication = new UsernamePasswordAuthenticationToken(userModel,
                                                                                      userModel.getPassword(),
                                                                                      userModel.getAuthorities());
        context.setAuthentication(authentication);
        return context;
    }

}
