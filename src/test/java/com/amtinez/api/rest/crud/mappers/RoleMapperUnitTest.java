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

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleMapperUnitTest {

    private static final Long ROLE_ID = 1L;
    private static final String ROLE_NAME = "testName";

    private static final int ROLE_USERS_SIZE = 1;

    @Mock
    private RoleModel roleModel;

    @Mock
    private UserModel userModel;

    @Mock
    private Role role;

    @Mock
    private User user;

    private final RoleMapper mapper = new RoleMapperImpl();

    @Before
    public void setUp() {
        when(roleModel.getId()).thenReturn(ROLE_ID);
        when(roleModel.getName()).thenReturn(ROLE_NAME);
        when(roleModel.getUsers()).thenReturn(Collections.singleton(userModel));

        when(role.getId()).thenReturn(ROLE_ID);
        when(role.getName()).thenReturn(ROLE_NAME);
        when(role.getUsers()).thenReturn(Collections.singleton(user));
    }

    @Test
    public void modelToDto() {
        final Role role = mapper.roleModelToRole(roleModel);
        assertEquals(ROLE_ID, role.getId());
        assertEquals(ROLE_NAME, role.getName());
        assertEquals(ROLE_USERS_SIZE, role.getUsers().size());
    }

    @Test
    public void nullModelToDto() {
        assertNull(mapper.roleModelToRole(null));
    }

    @Test
    public void nullUserModelsToDto() {
        when(roleModel.getUsers()).thenReturn(null);
        final Role role = mapper.roleModelToRole(roleModel);
        assertNull(role.getUsers());
    }

    @Test
    public void UserModelsWithNullUserModelToDto() {
        when(roleModel.getUsers()).thenReturn(Collections.singleton(null));
        final Role role = mapper.roleModelToRole(roleModel);
        assertNull(role.getUsers().iterator().next());
    }

    @Test
    public void dtoToModel() {
        final RoleModel roleModel = mapper.roleToRoleModel(role);
        assertEquals(ROLE_ID, roleModel.getId());
        assertEquals(ROLE_NAME, roleModel.getName());
        assertEquals(ROLE_USERS_SIZE, roleModel.getUsers().size());
    }

    @Test
    public void nullDtoToModel() {
        assertNull(mapper.roleToRoleModel(null));
    }

    @Test
    public void nullUsersToDto() {
        when(role.getUsers()).thenReturn(null);
        final RoleModel roleModel = mapper.roleToRoleModel(role);
        assertNull(roleModel.getUsers());
    }

    @Test
    public void UsersWithNullUserToDto() {
        when(role.getUsers()).thenReturn(Collections.singleton(null));
        final RoleModel roleModel = mapper.roleToRoleModel(role);
        assertNull(roleModel.getUsers().iterator().next());
    }

}
