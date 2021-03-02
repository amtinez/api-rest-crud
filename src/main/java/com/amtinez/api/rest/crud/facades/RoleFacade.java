package com.amtinez.api.rest.crud.facades;

import com.amtinez.api.rest.crud.dtos.Role;

import java.util.List;
import java.util.Optional;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public interface RoleFacade {

    /**
     * Retrieves the role with the given id
     *
     * @param id the id of the role
     * @return the role if found
     */
    Optional<Role> findRole(final Long id);

    /**
     * Retrieves the list of all roles
     *
     * @return the list of all roles
     */
    List<Role> findAllRoles();

    /**
     * Save the role
     *
     * @param role the role
     * @return the saved role
     */
    Role saveRole(final Role role);

    /**
     * Delete the role with the given id
     *
     * @param id the id of the role
     */
    void deleteRole(final Long id);

}
