package com.amtinez.api.rest.crud.services;

import com.amtinez.api.rest.crud.models.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

/**
 * @author amartinezcerro@gmail.com
 */
public interface UserService extends UserDetailsService {

    /**
     * Retrieves the user model with the given id
     *
     * @param id the id of the user model
     * @return the user model if found
     */
    Optional<UserModel> findUser(final Long id);

    /**
     * Retrieves the list of all user models
     *
     * @return the list of all user models
     */
    List<UserModel> findAllUsers();

    /**
     * Save the user model
     *
     * @param userModel the user model
     * @return the saved user model
     */
    UserModel saveUser(final UserModel userModel);

    /**
     * Delete the user model with the given id
     *
     * @param id the id of the user model
     */
    void deleteUser(final Long id);

}
