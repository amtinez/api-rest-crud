package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import com.amtinez.api.rest.crud.services.AuthorityService;
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
 * @author amartinezcerro@gmail.com
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.TEST)
@Transactional
public class AuthorityServiceIntegrationTest {

    //TODO - IN OTHER TASK - MORE TEST - DATABASE VALIDATIONS

    private static final Long EXISTING_ID = 1L;
    private static final String EXISTING_NAME = "Authority_One";
    private static final int EXISTING_AUTHORITIES_SIZE = 2;

    private static final String NAME = "Authority_Test";

    @Resource
    private AuthorityService authorityService;

    @Test
    public void testFindAuthority() {
        final Optional<AuthorityModel> authorityModelFound = authorityService.findAuthority(EXISTING_ID);
        assertTrue(authorityModelFound.isPresent());
        assertEquals(EXISTING_NAME, authorityModelFound.get().getName());
    }

    @Test
    public void testFindAllAuthorities() {
        final List<AuthorityModel> authorities = authorityService.findAllAuthorities();
        assertFalse(authorities.isEmpty());
        assertEquals(EXISTING_AUTHORITIES_SIZE, authorities.size());
    }

    @Test
    public void testSaveAuthority() {
        final AuthorityModel authorityModel = AuthorityModel.builder()
                                                            .name(NAME)
                                                            .build();
        final AuthorityModel authorityModelSaved = authorityService.saveAuthority(authorityModel);
        assertNotNull(authorityModelSaved);
        assertNotNull(authorityModelSaved.getId());
        assertEquals(NAME, authorityModelSaved.getName());
    }

    @Test
    public void testDeleteAuthority() {
        authorityService.removeAuthority(EXISTING_ID);
        assertTrue(authorityService.findAuthority(EXISTING_ID).isEmpty());
    }

}
