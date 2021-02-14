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

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorityMapperUnitTest {

    private static final Long AUTHORITY_ID = 1L;
    private static final String AUTHORITY_NAME = "testName";

    private static final int AUTHORITY_USERS_SIZE = 1;

    @Mock
    private AuthorityModel authorityModel;

    @Mock
    private UserModel userModel;

    @Mock
    private Authority authority;

    @Mock
    private User user;

    private final AuthorityMapper mapper = new AuthorityMapperImpl();

    @Before
    public void setUp() {
        when(authorityModel.getId()).thenReturn(AUTHORITY_ID);
        when(authorityModel.getName()).thenReturn(AUTHORITY_NAME);
        when(authorityModel.getUsers()).thenReturn(Collections.singleton(userModel));

        when(authority.getId()).thenReturn(AUTHORITY_ID);
        when(authority.getName()).thenReturn(AUTHORITY_NAME);
        when(authority.getUsers()).thenReturn(Collections.singleton(user));
    }

    @Test
    public void modelToDto() {
        final Authority authority = mapper.authorityModelToAuthority(authorityModel);
        assertEquals(AUTHORITY_ID, authority.getId());
        assertEquals(AUTHORITY_NAME, authority.getName());
        assertEquals(AUTHORITY_USERS_SIZE, authority.getUsers().size());
    }

    @Test
    public void nullModelToDto() {
        assertNull(mapper.authorityModelToAuthority(null));
    }

    @Test
    public void nullUserModelsToDto() {
        when(authorityModel.getUsers()).thenReturn(null);
        final Authority authority = mapper.authorityModelToAuthority(authorityModel);
        assertNull(authority.getUsers());
    }

    @Test
    public void UserModelsWithNullUserModelToDto() {
        when(authorityModel.getUsers()).thenReturn(Collections.singleton(null));
        final Authority authority = mapper.authorityModelToAuthority(authorityModel);
        assertNull(authority.getUsers().iterator().next());
    }

    @Test
    public void dtoToModel() {
        final AuthorityModel authorityModel = mapper.authorityToAuthorityModel(authority);
        assertEquals(AUTHORITY_ID, authorityModel.getId());
        assertEquals(AUTHORITY_NAME, authorityModel.getName());
        assertEquals(AUTHORITY_USERS_SIZE, authorityModel.getUsers().size());
    }

    @Test
    public void nullDtoToModel() {
        assertNull(mapper.authorityToAuthorityModel(null));
    }

    @Test
    public void nullUsersToDto() {
        when(authority.getUsers()).thenReturn(null);
        final AuthorityModel authorityModel = mapper.authorityToAuthorityModel(authority);
        assertNull(authorityModel.getUsers());
    }

    @Test
    public void UsersWithNullUserToDto() {
        when(authority.getUsers()).thenReturn(Collections.singleton(null));
        final AuthorityModel authorityModel = mapper.authorityToAuthorityModel(authority);
        assertNull(authorityModel.getUsers().iterator().next());
    }

}
