package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.annotations.MockUser;
import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.facades.UserFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(Profiles.TEST)
@Transactional
@MockUser
public class UserFacadeIntegrationTest {

    //TODO - IN OTHER TASK - MORE TEST

    private static final Long EXISTING_ID_ONE = 1L;
    private static final Long EXISTING_ID_TWO = 2L;
    private static final String EXISTING_FIRST_NAME = "User";
    private static final String EXISTING_LAST_NAME = "One";
    private static final int EXISTING_USERS_SIZE = 2;

    private static final String FIRST_NAME = "userTestFirstName";
    private static final String FIRST_NAME_UPDATE = "userTestFirstNameUpdate";
    private static final String LAST_NAME = "userTestLastName";
    private static final String EMAIL = "user@test.com";
    private static final String PASSWORD = "userTestPassword";
    private static final String LOCKED_REASON = "userTestLockedReason";
    private static final int USER_AUTHORITY_LIST_SIZE = 1;

    private static final Long AUTHORITY_EXISTING_ID = 1L;

    @Resource
    private UserFacade userFacade;

    @Test
    public void testFindUser() {
        final Optional<User> userFound = userFacade.findUser(EXISTING_ID_ONE);
        assertTrue(userFound.isPresent());
        assertEquals(EXISTING_FIRST_NAME, userFound.get().getFirstName());
        assertEquals(EXISTING_LAST_NAME, userFound.get().getLastName());
    }

    @Test
    public void testFindAllUsers() {
        final List<User> users = userFacade.findAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(EXISTING_USERS_SIZE, users.size());
    }

    @Test
    public void testRegisterUser() {
        final LocalDateTime localDateTimeNow = LocalDateTime.now();
        final Authority authority = Authority.builder()
                                             .id(AUTHORITY_EXISTING_ID)
                                             .build();
        final User user = User.builder()
                              .firstName(FIRST_NAME)
                              .lastName(LAST_NAME)
                              .email(EMAIL)
                              .password(PASSWORD)
                              .birthdayDate(localDateTimeNow)
                              .authorities(Collections.singleton(authority))
                              .build();
        final User userSaved = userFacade.registerUser(user);
        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
        assertEquals(FIRST_NAME, userSaved.getFirstName());
        assertEquals(LAST_NAME, userSaved.getLastName());
        assertEquals(EMAIL, userSaved.getEmail());
        assertNull(userSaved.getPassword());
        assertEquals(localDateTimeNow, userSaved.getBirthdayDate());
        assertEquals(Boolean.FALSE, userSaved.getEnabled());
        assertEquals(USER_AUTHORITY_LIST_SIZE, userSaved.getAuthorities().size());
        assertNotNull(userSaved.getCreatedBy());
        assertNotNull(userSaved.getCreatedDate());
        assertNotNull(userSaved.getLastUpdatedBy());
        assertNotNull(userSaved.getLastUpdatedDate());
    }

    @Test
    public void testEnableUser() {
        final int affectedUsers = userFacade.enableUser(EXISTING_ID_TWO);
        assertEquals(1, affectedUsers);
        final Optional<User> userFound = userFacade.findUser(EXISTING_ID_TWO);
        assertTrue(userFound.isPresent());
        assertTrue(userFound.get().getEnabled());
    }

    @Test
    public void testDisableUser() {
        final int affectedUsers = userFacade.disableUser(EXISTING_ID_ONE);
        assertEquals(1, affectedUsers);
        final Optional<User> userFound = userFacade.findUser(EXISTING_ID_TWO);
        assertTrue(userFound.isPresent());
        assertFalse(userFound.get().getEnabled());
    }

    @Test
    public void testDeleteUser() {
        userFacade.deleteUser(EXISTING_ID_ONE);
        assertTrue(userFacade.findUser(EXISTING_ID_ONE).isEmpty());
    }

    @Test
    public void testUpdateUser() {
        final User user = User.builder()
                              .id(EXISTING_ID_ONE)
                              .firstName(FIRST_NAME_UPDATE)
                              .build();
        userFacade.updateUser(user);
        assertEquals(EXISTING_ID_ONE, user.getId());
        assertEquals(FIRST_NAME_UPDATE, user.getFirstName());
    }

    public void testLockUser() {
        final int affectedUsers = userFacade.lockUser(EXISTING_ID_ONE, LOCKED_REASON);
        assertEquals(1, affectedUsers);
        Optional<User> user = userFacade.findUser(EXISTING_ID_ONE);
        assertTrue(user.isPresent());
        assertNotNull(user.get().getLockedBy());
        assertNotNull(user.get().getLockedDate());
        assertEquals(LOCKED_REASON, user.get().getLockedReason());
    }

    public void testUnlockUser() {
        final int affectedUsers = userFacade.unlockUser(EXISTING_ID_ONE);
        assertEquals(1, affectedUsers);
        Optional<User> user = userFacade.findUser(EXISTING_ID_ONE);
        assertTrue(user.isPresent());
        assertNull(user.get().getLockedBy());
        assertNull(user.get().getLockedDate());
        assertNull(user.get().getLockedReason());
    }

}
