package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.annotations.MockUser;
import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.services.RoleService;
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
public class RoleServiceIntegrationTest {

    //TODO - IN OTHER TASK - MORE TEST - DATABASE VALIDATIONS

    private static final Long EXISTING_ID = 1L;
    private static final String EXISTING_NAME = "Role_One";
    private static final int EXISTING_ROLES_SIZE = 3;

    private static final String NAME = "Role_Test";

    @Resource
    private RoleService roleService;

    @Test
    public void testFindRole() {
        final Optional<RoleModel> roleModelFound = roleService.findRole(EXISTING_ID);
        assertTrue(roleModelFound.isPresent());
        assertEquals(EXISTING_NAME, roleModelFound.get().getName());
    }

    @Test
    public void testFindAllRoles() {
        final List<RoleModel> roles = roleService.findAllRoles();
        assertFalse(roles.isEmpty());
        assertEquals(EXISTING_ROLES_SIZE, roles.size());
    }

    @Test
    public void testSaveRole() {
        final RoleModel roleModel = RoleModel.builder()
                                             .name(NAME)
                                             .build();
        final RoleModel roleModelSaved = roleService.saveRole(roleModel);
        assertNotNull(roleModelSaved);
        assertNotNull(roleModelSaved.getId());
        assertEquals(NAME, roleModelSaved.getName());
    }

    @Test
    public void testDeleteRole() {
        roleService.deleteRole(EXISTING_ID);
        assertTrue(roleService.findRole(EXISTING_ID).isEmpty());
    }

}
