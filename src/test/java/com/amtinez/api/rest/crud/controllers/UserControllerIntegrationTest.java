package com.amtinez.api.rest.crud.controllers;

import com.amtinez.api.rest.crud.annotations.WithMockAdminUser;
import com.amtinez.api.rest.crud.constants.ConfigurationConstants.Profiles;
import com.amtinez.api.rest.crud.dtos.Role;
import com.amtinez.api.rest.crud.dtos.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(Profiles.TEST)
@Transactional
public class UserControllerIntegrationTest {

    private static final String USER_CONTROLLER_URL = "/users";
    private static final String LOCK_REASON_PARAM = "reason";
    private static final String OTHER_ROLE_NAME = "OTHER";

    private static final String FIRST_NAME = "userTestFirstName";
    private static final String LAST_NAME = "userTestLastName";
    private static final String EMAIL = "user@test.com";
    private static final String PASSWORD = "userTestPassword";

    private static final Long ROLE_EXISTING_ID = 1L;

    @Resource
    private MockMvc mockMvc;

    @Test
    @WithMockAdminUser
    public void testFindUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser(roles = OTHER_ROLE_NAME)
    public void testFindUsersForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testFindUsersUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    public void testRegisterUser() throws Exception {
        final Role role = Role.builder()
                              .id(ROLE_EXISTING_ID)
                              .build();
        final User user = User.builder()
                              .firstName(FIRST_NAME)
                              .lastName(LAST_NAME)
                              .email(EMAIL)
                              .password(PASSWORD)
                              .birthdayDate(LocalDateTime.now())
                              .roles(Collections.singleton(role))
                              .build();
        final ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post(USER_CONTROLLER_URL)
                                              .contentType(MediaType.APPLICATION_JSON)
                                              .content(mapper.writeValueAsString(user)))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRegisterUserWithInvalidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_CONTROLLER_URL))
               .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockAdminUser
    public void testFindUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL + "/1"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockAdminUser
    public void testFindUserNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL + "/99"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser(roles = OTHER_ROLE_NAME)
    public void testFindUserForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL + "/1"))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testFindUserUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_CONTROLLER_URL + "/1"))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockAdminUser
    public void testDeleteUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USER_CONTROLLER_URL + "/1"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockAdminUser
    public void testDeleteUserNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USER_CONTROLLER_URL + "/99"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testDeleteUserForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USER_CONTROLLER_URL + "/1"))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testDeleteUserUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USER_CONTROLLER_URL + "/1"))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockAdminUser
    public void testEnableUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/enable"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockAdminUser
    public void testEnableNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/99/enable"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testEnableUserForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/enable"))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testEnableUserUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/enable"))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockAdminUser
    public void testDisableUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/disable"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockAdminUser
    public void testDisableNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/99/disable"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testDisableUserForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/disable"))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testDisableUserUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/disable"))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockAdminUser
    public void testLockUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/lock")
                                              .param(LOCK_REASON_PARAM, LOCK_REASON_PARAM))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockAdminUser
    public void testLockNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/99/lock")
                                              .param(LOCK_REASON_PARAM, LOCK_REASON_PARAM))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockAdminUser
    public void testLockUserNotValidReason() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/lock")
                                              .param(LOCK_REASON_PARAM, StringUtils.EMPTY))
               .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    @WithMockUser
    public void testLockUserForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/lock")
                                              .param(LOCK_REASON_PARAM, LOCK_REASON_PARAM))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testLockUserUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/lock")
                                              .param(LOCK_REASON_PARAM, LOCK_REASON_PARAM))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

    @Test
    @WithMockAdminUser
    public void testUnlockUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/unlock"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockAdminUser
    public void testUnlockNotExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/99/unlock"))
               .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    @WithMockUser
    public void testUnlockUserForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/unlock"))
               .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    public void testUnlockUserUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(USER_CONTROLLER_URL + "/1/unlock"))
               .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }

}
