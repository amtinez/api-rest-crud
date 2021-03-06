package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.security.impl.UserDetailsImpl;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class UserUtils {

    protected static final String ANONYMOUS_USER = "Anonymous User";

    /**
     * Returns the full name of the user
     *
     * @param userDetails the user
     * @return the full name of the user
     */
    public static String getFullName(final UserDetailsImpl userDetails) {
        return String.format("%s %s", userDetails.getFirstName(), userDetails.getLastName());
    }

    /**
     * Returns the full name of the connected user if exists, otherwise Anonymous User
     *
     * @param principal the user
     * @return the full name of the user if it exists, otherwise Anonymous User
     */
    public static String getPrincipalFullName(final Object principal) {
        return principal instanceof UserDetailsImpl ? getFullName((UserDetailsImpl) principal) : ANONYMOUS_USER;
    }

    private UserUtils() {
    }

}
