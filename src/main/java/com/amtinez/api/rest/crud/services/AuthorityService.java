package com.amtinez.api.rest.crud.services;

import com.amtinez.api.rest.crud.models.AuthorityModel;

import java.util.List;
import java.util.Optional;

/**
 * @author amartinezcerro@gmail.com
 */
public interface AuthorityService {

    /**
     * Retrieves the authority with the given id
     *
     * @param id the id of the authority
     * @return the authority if found
     */
    Optional<AuthorityModel> findAuthority(final Long id);

    /**
     * Retrieves the list of all authorities
     *
     * @return the list of all authorities
     */
    List<AuthorityModel> findAllAuthorities();

    /**
     * Save the authority
     *
     * @param authorityModel the authority
     * @return the saved authority
     */
    AuthorityModel saveAuthority(final AuthorityModel authorityModel);

    /**
     * Remove the authority with the given id
     *
     * @param id the id of the authority
     */
    void removeAuthority(final Long id);

}
