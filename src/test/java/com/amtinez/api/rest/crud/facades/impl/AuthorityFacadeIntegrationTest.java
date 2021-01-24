package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.annotations.MockUser;
import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.facades.AuthorityFacade;
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
@MockUser
public class AuthorityFacadeIntegrationTest {

    //TODO - IN OTHER TASK - MORE TEST

    private static final Long EXISTING_ID = 1L;
    private static final String EXISTING_NAME = "Authority_One";
    private static final int EXISTING_AUTHORITIES_SIZE = 2;

    private static final String NAME = "Authority_Test";

    @Resource
    private AuthorityFacade authorityFacade;

    @Test
    public void testFindAuthority() {
        final Optional<Authority> authorityFound = authorityFacade.findAuthority(EXISTING_ID);
        assertTrue(authorityFound.isPresent());
        assertEquals(EXISTING_NAME, authorityFound.get().getName());
    }

    @Test
    public void testFindAllAuthorities() {
        final List<Authority> authorities = authorityFacade.findAllAuthorities();
        assertFalse(authorities.isEmpty());
        assertEquals(EXISTING_AUTHORITIES_SIZE, authorities.size());
    }

    @Test
    public void testSaveAuthority() {
        final Authority authority = Authority.builder()
                                             .name(NAME)
                                             .build();
        final Authority authoritySaved = authorityFacade.saveAuthority(authority);
        assertNotNull(authoritySaved);
        assertNotNull(authoritySaved.getId());
        assertEquals(NAME, authoritySaved.getName());
    }

    @Test
    public void testDeleteAuthority() {
        authorityFacade.deleteAuthority(EXISTING_ID);
        assertTrue(authorityFacade.findAuthority(EXISTING_ID).isEmpty());
    }

}
