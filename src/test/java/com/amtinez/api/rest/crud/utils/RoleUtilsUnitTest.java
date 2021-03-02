package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.models.RoleModel;
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
public class RoleUtilsUnitTest {

    private static final String ROLE_NAME = "testName";

    @Mock
    private RoleModel roleModel;

    @Before
    public void setUp() {
        when(roleModel.getName()).thenReturn(ROLE_NAME);
    }

    @Test
    public void testGetPrefixedName() {
        assertEquals(String.format("ROLE_%s", ROLE_NAME), RoleUtils.getPrefixedName(roleModel));
    }

}
