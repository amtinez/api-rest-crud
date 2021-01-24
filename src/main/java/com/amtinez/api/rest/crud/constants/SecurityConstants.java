package com.amtinez.api.rest.crud.constants;

/**
 * @author amartinezcerro@gmail.com
 */
public class SecurityConstants {

    public static final int BCRYPT_PASSWORD_ENCODER_STRENGTH = 10;

    public static final int PASSWORD_LIFETIME_MONTHS = 6;
    public static final int INACTIVE_LIFETIME_MONTHS = 3;

    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_USER = "user";

    public static final String USER_EMAIL_NOT_FOUND = "User with the email: %s not found";

    private SecurityConstants() {
    }

}
