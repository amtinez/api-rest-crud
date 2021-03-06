package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserUtilsUnitTest {

    private static final String USER_FIRST_NAME = "testFirstName";
    private static final String USER_LAST_NAME = "testLastName";
    private static final String USER_FULL_NAME = String.format("%s %s", USER_FIRST_NAME, USER_LAST_NAME);

    @Mock
    private UserDetailsImpl userDetails;

    @Before
    public void setUp() {
        when(userDetails.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(userDetails.getLastName()).thenReturn(USER_LAST_NAME);
    }

    @Test
    public void testGetFullName() {
        assertEquals(USER_FULL_NAME, UserUtils.getFullName(userDetails));
    }

    @Test
    public void testGetPrincipalFullName() {
        assertEquals(USER_FULL_NAME, UserUtils.getPrincipalFullName(userDetails));
    }

    @Test
    public void testGetPrincipalFullNameAnonymousUser() {
        assertEquals(UserUtils.ANONYMOUS_USER, UserUtils.getPrincipalFullName(USER_FIRST_NAME));
    }

}
