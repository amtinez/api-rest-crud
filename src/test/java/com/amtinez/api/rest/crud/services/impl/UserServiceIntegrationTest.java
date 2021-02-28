package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.annotations.MockUser;
import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
public class UserServiceIntegrationTest {

    //TODO - IN OTHER TASK - MORE TEST - DATABASE VALIDATIONS

    private static final Long EXISTING_ID = 1L;
    private static final Long NOT_EXISTING_ID = 999L;
    private static final String EXISTING_FIRST_NAME = "User";
    private static final String EXISTING_LAST_NAME = "One";
    private static final String EXISTING_EMAIL = "user@one.com";
    private static final int EXISTING_USERS_SIZE = 2;

    private static final String FIRST_NAME = "userTestFirstName";
    private static final String LAST_NAME = "userTestLastName";
    private static final String EMAIL = "user@test.com";
    private static final String PASSWORD = "userTestPassword";
    private static final String LOCKED_BY = FIRST_NAME + LAST_NAME;
    private static final LocalDateTime LOCKED_DATE = LocalDateTime.now();
    private static final String LOCKED_REASON = "userTestLockedReason";

    @Resource
    private UserService userService;

    @Test
    public void testFindUser() {
        final Optional<UserModel> userModelFound = userService.findUser(EXISTING_ID);
        assertTrue(userModelFound.isPresent());
        assertEquals(EXISTING_FIRST_NAME, userModelFound.get().getFirstName());
        assertEquals(EXISTING_LAST_NAME, userModelFound.get().getLastName());
    }

    @Test
    public void testFindUserNotExists() {
        final Optional<UserModel> userModelFound = userService.findUser(NOT_EXISTING_ID);
        assertFalse(userModelFound.isPresent());
    }

    @Test
    public void testFindAllUsers() {
        final List<UserModel> users = userService.findAllUsers();
        assertFalse(users.isEmpty());
        assertEquals(EXISTING_USERS_SIZE, users.size());
    }

    @Test
    public void testSaveUser() {
        final LocalDateTime localDateTimeNow = LocalDateTime.now();
        final UserModel userModel = UserModel.builder()
                                             .firstName(FIRST_NAME)
                                             .lastName(LAST_NAME)
                                             .email(EMAIL)
                                             .password(PASSWORD)
                                             .birthdayDate(localDateTimeNow)
                                             .lastAccessDate(localDateTimeNow)
                                             .lastPasswordUpdateDate(localDateTimeNow)
                                             .enabled(Boolean.FALSE)
                                             .build();
        final UserModel userModelSaved = userService.saveUser(userModel);
        assertNotNull(userModelSaved);
        assertNotNull(userModelSaved.getId());
        assertEquals(FIRST_NAME, userModelSaved.getFirstName());
        assertEquals(LAST_NAME, userModelSaved.getLastName());
        assertEquals(EMAIL, userModelSaved.getEmail());
        assertEquals(EMAIL, userModelSaved.getUsername());
        assertFalse(userModelSaved.isEnabled());
        assertEquals(PASSWORD, userModelSaved.getPassword());
        assertEquals(localDateTimeNow, userModelSaved.getBirthdayDate());
        assertNull(userModelSaved.getLockedDate());
        assertTrue(userModelSaved.isAccountNonLocked());
        assertTrue(userModelSaved.isAccountNonExpired());
        assertTrue(userModelSaved.isCredentialsNonExpired());
        assertEquals(localDateTimeNow, userModelSaved.getLastAccessDate());
        assertEquals(localDateTimeNow, userModelSaved.getLastPasswordUpdateDate());
        assertNotNull(userModelSaved.getCreatedBy());
        assertNotNull(userModelSaved.getCreatedDate());
        assertNotNull(userModelSaved.getLastUpdatedBy());
        assertNotNull(userModelSaved.getLastUpdatedDate());
    }

    @Test
    public void testDeleteUser() {
        userService.deleteUser(EXISTING_ID);
        assertTrue(userService.findUser(EXISTING_ID).isEmpty());
    }

    @Test
    public void testExistsUserEmail() {
        assertTrue(userService.existsUserEmail(EXISTING_EMAIL));
    }

    @Test
    public void testEnableUser() {
        assertEquals(1, userService.updateUserEnabledStatus(EXISTING_ID, Boolean.TRUE));
    }

    @Test
    public void testEnableUserNotExists() {
        assertEquals(0, userService.updateUserEnabledStatus(NOT_EXISTING_ID, Boolean.TRUE));
    }

    @Test
    public void testLockUser() {
        assertEquals(1, userService.updateUserLockedInformation(EXISTING_ID, LOCKED_BY, LOCKED_DATE, LOCKED_REASON));
    }

    @Test
    public void testLockUserNotExists() {
        assertEquals(0, userService.updateUserLockedInformation(NOT_EXISTING_ID, LOCKED_BY, LOCKED_DATE, LOCKED_REASON));
    }

    @Test
    public void testLoadUserByUsernameExists() {
        assertNotNull(userService.loadUserByUsername(EXISTING_EMAIL));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testExistingLoadUserByUsernameNotExists() {
        userService.loadUserByUsername(EMAIL);
    }

}
