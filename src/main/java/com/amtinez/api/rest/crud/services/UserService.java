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
     * Retrieves the user with the given id
     *
     * @param id the id of the user
     * @return the user if found
     */
    Optional<UserModel> findUser(final Long id);

    /**
     * Retrieves the list of all users
     *
     * @return the list of all users
     */
    List<UserModel> findAllUsers();

    /**
     * Save the user
     *
     * @param userModel the user
     * @return the saved user
     */
    UserModel saveUser(final UserModel userModel);

    /**
     * Remove the user with the given id
     *
     * @param id the id of the user
     */
    void removeUser(final Long id);

}
