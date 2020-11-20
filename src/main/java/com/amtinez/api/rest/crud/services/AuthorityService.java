package com.amtinez.api.rest.crud.services;

import com.amtinez.api.rest.crud.models.AuthorityModel;

import java.util.List;
import java.util.Optional;

/**
 * @author amartinezcerro@gmail.com
 */
public interface AuthorityService {

    /**
     * Retrieves the authority model with the given id
     *
     * @param id the id of the authority model
     * @return the authority model if found
     */
    Optional<AuthorityModel> findAuthority(final Long id);

    /**
     * Retrieves the list of all authority models
     *
     * @return the list of all authority models
     */
    List<AuthorityModel> findAllAuthorities();

    /**
     * Save the authority model
     *
     * @param authorityModel the authority model
     * @return the saved authority model
     */
    AuthorityModel saveAuthority(final AuthorityModel authorityModel);

    /**
     * Remove the authority model with the given id
     *
     * @param id the id of the authority model
     */
    void removeAuthority(final Long id);

}
