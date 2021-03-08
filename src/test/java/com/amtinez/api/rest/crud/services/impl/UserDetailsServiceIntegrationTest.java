package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertNotNull;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.TEST)
public class UserDetailsServiceIntegrationTest {

    private static final String EXISTING_EMAIL = "user@one.com";
    private static final String NOT_EXISTING_EMAIL = "user@test.com";

    @Resource
    private UserDetailsService userDetailsService;

    @Test
    public void testLoadUserByUsernameExists() {
        assertNotNull(userDetailsService.loadUserByUsername(EXISTING_EMAIL));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testExistingLoadUserByUsernameNotExists() {
        userDetailsService.loadUserByUsername(NOT_EXISTING_EMAIL);
    }

}
