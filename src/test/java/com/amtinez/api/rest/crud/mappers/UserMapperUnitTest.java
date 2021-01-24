package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import com.amtinez.api.rest.crud.models.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author amartinezcerro@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class UserMapperUnitTest {

    private static final Long USER_ID = 1L;
    private static final String USER_FIRST_NAME = "testFirstName";
    private static final String USER_LAST_NAME = "testLastName";
    private static final String USER_EMAIL = "test@email.com";
    private static final LocalDateTime USER_BIRTHDAY_DATE = LocalDateTime.now();

    private static final Long AUTHORITY_ID = 1L;
    private static final String AUTHORITY_NAME = "testName";

    private static final int USER_AUTHORITIES_SIZE = 1;

    @Mock
    private UserModel userModel;

    @Mock
    private AuthorityModel authorityModel;

    @Mock
    private User user;

    @Mock
    private Authority authority;

    private final UserMapper mapper = new UserMapperImpl();

    @Before
    public void setUp() {
        when(userModel.getId()).thenReturn(USER_ID);
        when(userModel.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(userModel.getLastName()).thenReturn(USER_LAST_NAME);
        when(userModel.getEmail()).thenReturn(USER_EMAIL);
        when(userModel.getBirthdayDate()).thenReturn(USER_BIRTHDAY_DATE);

        when(authorityModel.getId()).thenReturn(AUTHORITY_ID);
        when(authorityModel.getName()).thenReturn(AUTHORITY_NAME);
        when(userModel.getAuthorities()).thenReturn(Collections.singleton(authorityModel));

        when(user.getId()).thenReturn(USER_ID);
        when(user.getFirstName()).thenReturn(USER_FIRST_NAME);
        when(user.getLastName()).thenReturn(USER_LAST_NAME);
        when(user.getEmail()).thenReturn(USER_EMAIL);
        when(user.getBirthdayDate()).thenReturn(USER_BIRTHDAY_DATE);
        when(authority.getId()).thenReturn(AUTHORITY_ID);
        when(authority.getName()).thenReturn(AUTHORITY_NAME);
        when(user.getAuthorities()).thenReturn(Collections.singleton(authority));
    }

    @Test
    public void modelToDto() {
        User user = mapper.userModelToUser(userModel);
        assertEquals(USER_FIRST_NAME, user.getFirstName());
        assertEquals(USER_LAST_NAME, user.getLastName());
        assertEquals(USER_EMAIL, user.getEmail());
        assertEquals(USER_BIRTHDAY_DATE, user.getBirthdayDate());
        final Set<Authority> authorities = user.getAuthorities();
        assertEquals(USER_AUTHORITIES_SIZE, authorities.size());
        final Authority authority = authorities.iterator().next();
        assertEquals(AUTHORITY_ID, authority.getId());
        assertEquals(AUTHORITY_NAME, authority.getName());
    }

    @Test
    public void modelToDtoNullAuthorities() {
        when(userModel.getAuthorities()).thenReturn(null);
        final User user = mapper.userModelToUser(userModel);
        assertNull(user.getAuthorities());
    }

    @Test
    public void modelToDtoNullAuthority() {
        when(userModel.getAuthorities()).thenReturn(Collections.singleton(null));
        final User user = mapper.userModelToUser(userModel);
        assertNull(user.getAuthorities().iterator().next());
    }

    @Test
    public void nullModelToDto() {
        assertNull(mapper.userModelToUser(null));
    }

    @Test
    public void dtoToModel() {
        UserModel userModel = mapper.userToUserModel(user);
        assertEquals(USER_FIRST_NAME, userModel.getFirstName());
        assertEquals(USER_LAST_NAME, userModel.getLastName());
        assertEquals(USER_EMAIL, userModel.getEmail());
        assertEquals(USER_BIRTHDAY_DATE, userModel.getBirthdayDate());
        final Set<AuthorityModel> authorities = userModel.getAuthorities();
        assertEquals(USER_AUTHORITIES_SIZE, authorities.size());
        final AuthorityModel authority = authorities.iterator().next();
        assertEquals(AUTHORITY_ID, authority.getId());
        assertEquals(AUTHORITY_NAME, authority.getName());
    }

    @Test
    public void dtoToModelNullAuthorities() {
        when(user.getAuthorities()).thenReturn(null);
        final UserModel userModel = mapper.userToUserModel(user);
        assertNull(userModel.getAuthorities());
    }

    @Test
    public void dtoToModelNullAuthority() {
        when(user.getAuthorities()).thenReturn(Collections.singleton(null));
        final UserModel userModel = mapper.userToUserModel(user);
        assertNull(userModel.getAuthorities().iterator().next());
    }

    @Test
    public void nullDtoToModel() {
        assertNull(mapper.userToUserModel(null));
    }

}
