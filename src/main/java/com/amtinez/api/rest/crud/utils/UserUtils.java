package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class UserUtils {

    /**
     * Returns the full name of the user
     *
     * @param userDetails the user
     * @return the full name of the user
     */
    public static String getFullName(final UserDetailsImpl userDetails) {
        return String.format("%s %s", userDetails.getFirstName(), userDetails.getLastName());
    }

    private UserUtils() {
    }

}
