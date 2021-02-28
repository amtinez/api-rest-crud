package com.amtinez.api.rest.crud.controllers;

import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.facades.UserFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerUnitTest {

    private static final int ONE_ROW_AFFECTED = 1;
    private static final int NO_ROW_AFFECTED = 0;

    @Mock
    private User user;

    @Mock
    private UserFacade userFacade;

    @InjectMocks
    private UserController userController;

    @Test
    public void testFindAllUsers() {
        when(userFacade.findAllUsers()).thenReturn(Collections.singletonList(user));
        final ResponseEntity<List<User>> userListResponseEntity = userController.findAllUsers();
        assertEquals(HttpStatus.OK, userListResponseEntity.getStatusCode());
        assertNotNull(userListResponseEntity.getBody());
        assertFalse(userListResponseEntity.getBody().isEmpty());
    }

    @Test
    public void testRegisterUsers() {
        when(userFacade.registerUser(user)).thenReturn(user);
        final ResponseEntity<User> userResponseEntity = userController.registerUser(user);
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNotNull(userResponseEntity.getBody());
    }

    @Test
    public void testFindUserExists() {
        when(userFacade.findUser(anyLong())).thenReturn(Optional.of(user));
        final ResponseEntity<User> userResponseEntity = userController.findUser(anyLong());
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNotNull(userResponseEntity.getBody());
    }

    @Test
    public void testFindUserNotExists() {
        when(userFacade.findUser(anyLong())).thenReturn(Optional.empty());
        final ResponseEntity<User> userResponseEntity = userController.findUser(anyLong());
        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testDeleteUserExists() {
        when(userFacade.findUser(anyLong())).thenReturn(Optional.of(user));
        final ResponseEntity<Void> userResponseEntity = userController.deleteUser(anyLong());
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testDeleteUserNotExists() {
        when(userFacade.findUser(anyLong())).thenReturn(Optional.empty());
        final ResponseEntity<Void> userResponseEntity = userController.deleteUser(anyLong());
        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testEnableUserExists() {
        when(userFacade.enableUser(anyLong())).thenReturn(ONE_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.enableUser(anyLong());
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testEnableUserNotExists() {
        when(userFacade.enableUser(anyLong())).thenReturn(NO_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.enableUser(anyLong());
        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testDisableUserExists() {
        when(userFacade.disableUser(anyLong())).thenReturn(ONE_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.disableUser(anyLong());
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testDisableUserNotExists() {
        when(userFacade.disableUser(anyLong())).thenReturn(NO_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.disableUser(anyLong());
        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testLockUserExists() {
        when(userFacade.lockUser(anyLong(), anyString())).thenReturn(ONE_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.lockUser(anyLong(), anyString());
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testLockUserNotExists() {
        when(userFacade.lockUser(anyLong(), anyString())).thenReturn(NO_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.lockUser(anyLong(), anyString());
        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testUnlockUserExists() {
        when(userFacade.unlockUser(anyLong())).thenReturn(ONE_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.unlockUser(anyLong());
        assertEquals(HttpStatus.OK, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

    @Test
    public void testUnlockUserNotExists() {
        when(userFacade.unlockUser(anyLong())).thenReturn(NO_ROW_AFFECTED);
        final ResponseEntity<Void> userResponseEntity = userController.unlockUser(anyLong());
        assertEquals(HttpStatus.NOT_FOUND, userResponseEntity.getStatusCode());
        assertNull(userResponseEntity.getBody());
    }

}
