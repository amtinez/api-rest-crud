package com.amtinez.api.rest.crud.facades;

import com.amtinez.api.rest.crud.dtos.Authority;

import java.util.List;
import java.util.Optional;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public interface AuthorityFacade {

    /**
     * Retrieves the authority with the given id
     *
     * @param id the id of the authority
     * @return the authority if found
     */
    Optional<Authority> findAuthority(final Long id);

    /**
     * Retrieves the list of all authorities
     *
     * @return the list of all authorities
     */
    List<Authority> findAllAuthorities();

    /**
     * Save the authority
     *
     * @param authority the authority
     * @return the saved authority
     */
    Authority saveAuthority(final Authority authority);

    /**
     * Delete the authority with the given id
     *
     * @param id the id of the authority
     */
    void deleteAuthority(final Long id);

}
