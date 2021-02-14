package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.models.UserModel;
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

    @Mock
    private UserModel userModel;

    @Before
    public void setUp() {
        when(userModel.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(userModel.getLastName()).thenReturn(USER_LAST_NAME);
    }

    @Test
    public void testGetFullName() {
        assertEquals(String.format("%s %s", USER_FIRST_NAME, USER_LAST_NAME), UserUtils.getFullName(userModel));
    }

}
