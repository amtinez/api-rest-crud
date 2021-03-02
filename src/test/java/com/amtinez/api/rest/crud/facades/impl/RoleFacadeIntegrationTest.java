package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.annotations.MockUser;
import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.dtos.Role;
import com.amtinez.api.rest.crud.facades.RoleFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.TEST)
@Transactional
@MockUser
public class RoleFacadeIntegrationTest {

    private static final Long EXISTING_ID = 1L;
    private static final String EXISTING_NAME = "Role_One";
    private static final int EXISTING_ROLES_SIZE = 3;

    private static final String NAME = "Role_Test";

    @Resource
    private RoleFacade roleFacade;

    @Test
    public void testFindRole() {
        final Optional<Role> roleFound = roleFacade.findRole(EXISTING_ID);
        assertTrue(roleFound.isPresent());
        assertEquals(EXISTING_NAME, roleFound.get().getName());
    }

    @Test
    public void testFindAllRoles() {
        final List<Role> roles = roleFacade.findAllRoles();
        assertFalse(roles.isEmpty());
        assertEquals(EXISTING_ROLES_SIZE, roles.size());
    }

    @Test
    public void testSaveRole() {
        final Role role = Role.builder()
                              .name(NAME)
                              .build();
        final Role roleSaved = roleFacade.saveRole(role);
        assertNotNull(roleSaved);
        assertNotNull(roleSaved.getId());
        assertEquals(NAME, roleSaved.getName());
    }

    @Test
    public void testDeleteRole() {
        roleFacade.deleteRole(EXISTING_ID);
        assertTrue(roleFacade.findRole(EXISTING_ID).isEmpty());
    }

}
