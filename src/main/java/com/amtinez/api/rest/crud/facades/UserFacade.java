package com.amtinez.api.rest.crud.facades;

import com.amtinez.api.rest.crud.dtos.User;

import java.util.List;
import java.util.Optional;

/**
 * @author amartinezcerro@gmail.com
 */
public interface UserFacade {

    /**
     * Retrieves the user with the given id
     *
     * @param id the id of the user
     * @return the user if found
     */
    Optional<User> findUser(final Long id);

    /**
     * Retrieves the list of all users
     *
     * @return the list of all users
     */
    List<User> findAllUsers();

    /**
     * Register the user
     *
     * @param user the user
     * @return the registered user
     */
    User registerUser(final User user);

    /**
     * Enables the user
     *
     * @param id the id of the user
     * @return number of updated users
     */
    int enableUser(final Long id);

    /**
     * Disables the user
     *
     * @param id the id of the user
     * @return number of updated users
     */
    int disableUser(final Long id);

    /**
     * Delete the user with the given id
     *
     * @param id the id of the user
     */
    void deleteUser(final Long id);

    /**
     * Update the user
     *
     * @param user the user
     * @return the updated user
     */
    User updateUser(final User user);

    /**
     * Locks the user
     *
     * @param id           the id of the user
     * @param lockedReason the reason of the lock
     * @return number of updated users
     */
    int lockUser(final Long id, final String lockedReason);

    /**
     * Unlocks the user
     *
     * @param id the id of the user
     * @return number of updated users
     */
    int unlockUser(final Long id);

}
