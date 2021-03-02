package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsMapperUnitTest {

    private static final Long USER_ID = 1L;
    private static final String USER_FIRST_NAME = "testFirstName";
    private static final String USER_LAST_NAME = "testLastName";
    private static final String USER_PASSWORD = "testPassword";
    private static final String USER_EMAIL = "test@email.com";
    private static final LocalDateTime EXPIRED_LOCAL_DATE = LocalDateTime.of(2020, 1, 1, 0, 0);

    private static final String ROLE_NAME = "testName";

    private static final int ROLE_USERS_SIZE = 1;

    @Mock
    private RoleModel roleModel;

    @Mock
    private UserModel userModel;

    private final UserDetailsMapper mapper = new UserDetailsMapperImpl();

    @Before
    public void setUp() {
        when(userModel.getId()).thenReturn(USER_ID);
        when(userModel.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(userModel.getLastName()).thenReturn(USER_LAST_NAME);
        when(userModel.getPassword()).thenReturn(USER_PASSWORD);
        when(userModel.getEmail()).thenReturn(USER_EMAIL);
        when(userModel.getEnabled()).thenReturn(Boolean.TRUE);
        when(userModel.getLastAccessDate()).thenReturn(LocalDateTime.now());
        when(userModel.getLastPasswordUpdateDate()).thenReturn(LocalDateTime.now());

        when(roleModel.getName()).thenReturn(ROLE_NAME);
        when(userModel.getRoles()).thenReturn(Collections.singleton(roleModel));
    }

    @Test
    public void userModelToUserDetails() {
        final UserDetailsImpl userDetails = mapper.userModelToUserDetails(userModel);
        assertEquals(USER_ID, userDetails.getId());
        assertEquals(USER_FIRST_NAME, userDetails.getFirstName());
        assertEquals(USER_LAST_NAME, userDetails.getLastName());
        assertEquals(USER_EMAIL, userDetails.getUsername());
        assertEquals(USER_PASSWORD, userDetails.getPassword());
        assertEquals(Boolean.TRUE, userDetails.isEnabled());
        assertEquals(ROLE_USERS_SIZE, userDetails.getAuthorities().size());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void userModelToUserDetailsExpired() {
        when(userModel.getLockedDate()).thenReturn(LocalDateTime.now());
        when(userModel.getLastAccessDate()).thenReturn(EXPIRED_LOCAL_DATE);
        when(userModel.getLastPasswordUpdateDate()).thenReturn(EXPIRED_LOCAL_DATE);
        final UserDetailsImpl userDetails = mapper.userModelToUserDetails(userModel);
        assertFalse(userDetails.isAccountNonLocked());
        assertFalse(userDetails.isAccountNonExpired());
        assertFalse(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void userModelToUserDetailsNullExpiredDates() {
        when(userModel.getLastAccessDate()).thenReturn(null);
        when(userModel.getLastPasswordUpdateDate()).thenReturn(null);
        final UserDetailsImpl userDetails = mapper.userModelToUserDetails(userModel);
        assertFalse(userDetails.isAccountNonExpired());
        assertFalse(userDetails.isCredentialsNonExpired());
    }

    @Test
    public void nullUserModelToUserDetails() {
        assertNull(mapper.userModelToUserDetails(null));
    }

}
