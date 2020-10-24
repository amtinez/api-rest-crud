package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * @author amartinezcerro@gmail.com
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorityMapperUnitTest {

    private static final Long AUTHORITY_ID = 1L;
    private static final String AUTHORITY_NAME = "testName";

    @Mock
    private AuthorityModel authorityModel;

    @Mock
    private Authority authority;

    private final AuthorityMapper mapper = new AuthorityMapperImpl();

    @Before
    public void setUp() {
        when(authorityModel.getId()).thenReturn(AUTHORITY_ID);
        when(authorityModel.getName()).thenReturn(AUTHORITY_NAME);

        when(authority.getId()).thenReturn(AUTHORITY_ID);
        when(authority.getName()).thenReturn(AUTHORITY_NAME);
    }

    @Test
    public void modelToDto() {
        Authority authority = mapper.authorityModelToAuthority(authorityModel);
        assertEquals(AUTHORITY_ID, authority.getId());
        assertEquals(AUTHORITY_NAME, authority.getName());
    }

    @Test
    public void nullModelToDto() {
        assertNull(mapper.authorityModelToAuthority(null));
    }

    @Test
    public void dtoToModel() {
        AuthorityModel authorityModel = mapper.authorityToAuthorityModel(authority);
        assertEquals(AUTHORITY_ID, authorityModel.getId());
        assertEquals(AUTHORITY_NAME, authorityModel.getName());
    }

    @Test
    public void nullDtoToModel() {
        assertNull(mapper.authorityToAuthorityModel(null));
    }

}
