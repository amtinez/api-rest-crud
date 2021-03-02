package com.amtinez.api.rest.crud.services;

import com.amtinez.api.rest.crud.models.RoleModel;

import java.util.List;
import java.util.Optional;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public interface RoleService {

    /**
     * Retrieves the role model with the given id
     *
     * @param id the id of the role model
     * @return the role model if found
     */
    Optional<RoleModel> findRole(final Long id);

    /**
     * Retrieves the list of all role models
     *
     * @return the list of all role models
     */
    List<RoleModel> findAllRoles();

    /**
     * Save the role model
     *
     * @param roleModel the role model
     * @return the saved role model
     */
    RoleModel saveRole(final RoleModel roleModel);

    /**
     * Delete the role model with the given id
     *
     * @param id the id of the role model
     */
    void deleteRole(final Long id);

}
