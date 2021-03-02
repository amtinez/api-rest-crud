package com.amtinez.api.rest.crud.services;

import com.amtinez.api.rest.crud.models.UserModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public interface UserService {

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

    /**
     * Retrieves if exists an user with the given email address
     *
     * @param email the email of the user model
     * @return if email address exists
     */
    boolean existsUserEmail(final String email);

    /**
     * Enables or disables the user if it exists
     *
     * @param id      the id of the user
     * @param enabled the enabled of the user
     * @return number of updated users
     */
    int updateUserEnabledStatus(final Long id, final Boolean enabled);

    /**
     * Lock or unlock the user if it exists
     *
     * @param id           the id of the user
     * @param lockedBy     the full name of the lock executor
     * @param lockedDate   the date of the lock
     * @param lockedReason the reason of the lock
     * @return number of updated users
     */
    int updateUserLockedInformation(final Long id, final String lockedBy, final LocalDateTime lockedDate, final String lockedReason);

}
