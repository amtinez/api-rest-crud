package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Role;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.models.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserMapperUnitTest {

    private static final Long USER_ID = 1L;
    private static final String USER_FIRST_NAME = "testFirstName";
    private static final String USER_LAST_NAME = "testLastName";
    private static final String USER_PASSWORD = "testPassword";
    private static final String USER_EMAIL = "test@email.com";
    private static final LocalDateTime USER_BIRTHDAY_DATE = LocalDateTime.now();
    private static final String USER_LOCKED_BY = String.format("%s %s", USER_FIRST_NAME, USER_LAST_NAME);
    private static final LocalDateTime USER_LOCKED_DATE = LocalDateTime.now();
    private static final String USER_LOCKED_REASON = "testLockedReason";

    private static final Long ROLE_ID = 1L;
    private static final String ROLE_NAME = "testName";

    private static final int USER_ROLES_SIZE = 1;

    @Mock
    private UserModel userModel;

    @Mock
    private RoleModel roleModel;

    @Mock
    private User user;

    @Mock
    private Role role;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final UserMapper mapper = new UserMapperImpl();

    @Before
    public void setUp() {
        when(userModel.getId()).thenReturn(USER_ID);
        when(userModel.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(userModel.getLastName()).thenReturn(USER_LAST_NAME);
        when(userModel.getEmail()).thenReturn(USER_EMAIL);
        when(userModel.getBirthdayDate()).thenReturn(USER_BIRTHDAY_DATE);
        when(userModel.getLockedBy()).thenReturn(USER_LOCKED_BY);
        when(userModel.getLockedDate()).thenReturn(USER_LOCKED_DATE);
        when(userModel.getLockedReason()).thenReturn(USER_LOCKED_REASON);

        when(roleModel.getId()).thenReturn(ROLE_ID);
        when(roleModel.getName()).thenReturn(ROLE_NAME);
        when(userModel.getRoles()).thenReturn(Collections.singleton(roleModel));

        when(user.getId()).thenReturn(USER_ID);
        when(user.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(user.getLastName()).thenReturn(USER_LAST_NAME);
        when(user.getPassword()).thenReturn(USER_PASSWORD);
        when(user.getEmail()).thenReturn(USER_EMAIL);
        when(user.getBirthdayDate()).thenReturn(USER_BIRTHDAY_DATE);
        when(user.getLockedBy()).thenReturn(USER_LOCKED_BY);
        when(user.getLockedDate()).thenReturn(USER_LOCKED_DATE);
        when(user.getLockedReason()).thenReturn(USER_LOCKED_REASON);
        when(role.getId()).thenReturn(ROLE_ID);
        when(role.getName()).thenReturn(ROLE_NAME);
        when(user.getRoles()).thenReturn(Collections.singleton(role));
    }

    @Test
    public void modelToDto() {
        User user = mapper.userModelToUser(userModel);
        assertEquals(USER_FIRST_NAME, user.getFirstName());
        assertEquals(USER_LAST_NAME, user.getLastName());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(USER_BIRTHDAY_DATE, user.getBirthdayDate());
        assertEquals(USER_LOCKED_BY, user.getLockedBy());
        assertEquals(USER_LOCKED_DATE, user.getLockedDate());
        assertEquals(USER_LOCKED_REASON, user.getLockedReason());
        final Set<Role> roles = user.getRoles();
        assertEquals(USER_ROLES_SIZE, roles.size());
        final Role role = roles.iterator().next();
        assertEquals(ROLE_ID, role.getId());
        assertEquals(ROLE_NAME, role.getName());
    }

    @Test
    public void modelToDtoNullRoles() {
        when(userModel.getRoles()).thenReturn(null);
        final User user = mapper.userModelToUser(userModel);
        assertNull(user.getRoles());
    }

    @Test
    public void modelToDtoNullRole() {
        when(userModel.getRoles()).thenReturn(Collections.singleton(null));
        final User user = mapper.userModelToUser(userModel);
        assertNull(user.getRoles().iterator().next());
    }

    @Test
    public void nullModelToDto() {
        assertNull(mapper.userModelToUser(null));
    }

    @Test
    public void dtoToModel() {
        UserModel userModel = mapper.userToUserModel(user);
        assertEquals(USER_FIRST_NAME, userModel.getFirstName());
        assertEquals(USER_LAST_NAME, userModel.getLastName());
        assertEquals(USER_EMAIL, userModel.getEmail());
        assertEquals(USER_BIRTHDAY_DATE, userModel.getBirthdayDate());
        assertEquals(USER_LOCKED_BY, userModel.getLockedBy());
        assertEquals(USER_LOCKED_DATE, userModel.getLockedDate());
        assertEquals(USER_LOCKED_REASON, userModel.getLockedReason());
        final Set<RoleModel> roles = userModel.getRoles();
        assertEquals(USER_ROLES_SIZE, roles.size());
        final RoleModel role = roles.iterator().next();
        assertEquals(ROLE_ID, role.getId());
        assertEquals(ROLE_NAME, role.getName());
    }

    @Test
    public void dtoToModelNullRoles() {
        when(user.getRoles()).thenReturn(null);
        final UserModel userModel = mapper.userToUserModel(user);
        assertNull(userModel.getRoles());
    }

    @Test
    public void dtoToModelNullRole() {
        when(user.getRoles()).thenReturn(Collections.singleton(null));
        final UserModel userModel = mapper.userToUserModel(user);
        assertNull(userModel.getRoles().iterator().next());
    }

    @Test
    public void nullDtoToModel() {
        assertNull(mapper.userToUserModel(null));
    }

    @Test
    public void dtoToModelRegisterStep() {
        UserModel userModel = mapper.userToUserModelRegisterStep(user, passwordEncoder);
        assertEquals(USER_FIRST_NAME, userModel.getFirstName());
        assertEquals(USER_LAST_NAME, userModel.getLastName());
        assertNotEquals(USER_PASSWORD, userModel.getPassword());
        assertEquals(USER_EMAIL, userModel.getEmail());
        assertEquals(USER_BIRTHDAY_DATE, userModel.getBirthdayDate());
        assertEquals(USER_LOCKED_BY, userModel.getLockedBy());
        assertEquals(USER_LOCKED_DATE, userModel.getLockedDate());
        assertEquals(USER_LOCKED_REASON, userModel.getLockedReason());
        final Set<RoleModel> roles = userModel.getRoles();
        assertEquals(USER_ROLES_SIZE, roles.size());
        final RoleModel role = roles.iterator().next();
        assertEquals(ROLE_ID, role.getId());
        assertEquals(ROLE_NAME, role.getName());
    }

    @Test
    public void nullDtoToModelRegisterStep() {
        assertNull(mapper.userToUserModelRegisterStep(null, passwordEncoder));
    }

    @Test
    public void dtoToModelRegisterStepNullPasswordEncoder() {
        UserModel userModel = mapper.userToUserModelRegisterStep(user, null);
        assertEquals(USER_PASSWORD, userModel.getPassword());
    }

}
