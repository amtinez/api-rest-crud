package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.models.UserModel;

/**
 * @author amartinezcerro@gmail.com
 */
public class UserUtils {

    /**
     * Returns the full name of the user
     *
     * @param userModel the user
     * @return the full name of the user
     */
    public static String getFullName(final UserModel userModel) {
        return String.format("%s %s", userModel.getFirstName(), userModel.getLastName());
    }

    private UserUtils() {
    }

}
