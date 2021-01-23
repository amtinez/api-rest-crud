package com.amtinez.api.rest.crud.daos;

import com.amtinez.api.rest.crud.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author amartinezcerro@gmail.com
 */
public interface UserDao extends JpaRepository<UserModel, Long> {

    /**
     * Retrieves the user with the given email address
     *
     * @param email the email of the user
     * @return the user if found
     */
    UserModel findByEmail(final String email);

    /**
     * Retrieves if exists an user with the given email address
     *
     * @param email the email of the user
     * @return if email address exists
     */
    boolean existsByEmail(final String email);

}
