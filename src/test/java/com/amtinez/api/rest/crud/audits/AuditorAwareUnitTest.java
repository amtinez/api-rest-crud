package com.amtinez.api.rest.crud.audits;

import com.amtinez.api.rest.crud.audits.impl.AuditorAwareImpl;
import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;
import com.amtinez.api.rest.crud.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class AuditorAwareUnitTest {

    private static final String FIRST_NAME = "userTestFirstName";
    private static final String LAST_NAME = "userTestLastName";

    @Mock
    private UserDetailsImpl userDetails;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private AuditorAwareImpl auditorAware;

    @Before
    public void setUp() {
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

    @Test
    public void testGetCurrentAuditor() {
        when(userDetails.getFirstName()).thenReturn(FIRST_NAME);
        when(userDetails.getLastName()).thenReturn(LAST_NAME);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        final Optional<String> currentAuditorFullName = auditorAware.getCurrentAuditor();
        Assert.assertTrue(currentAuditorFullName.isPresent());
        Assert.assertEquals(UserUtils.getFullName(userDetails), currentAuditorFullName.get());
    }

    @Test
    public void testGetCurrentAuditorNotExists() {
        when(authentication.getPrincipal()).thenReturn(null);
        final Optional<String> currentAuditorFullName = auditorAware.getCurrentAuditor();
        Assert.assertFalse(currentAuditorFullName.isPresent());
    }

}
