package com.amtinez.api.rest.crud.constants;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class ValidationConstants {

    public static class User {

        public static final int FIRST_NAME_MAX_FIELD_LENGTH = 50;
        public static final int LAST_NAME_MAX_FIELD_LENGTH = 50;
        public static final int EMAIL_MAX_FIELD_LENGTH = 50;
        public static final int PASSWORD_MAX_FIELD_LENGTH = 80;
        public static final int LOCKED_REASON_MAX_FIELD_LENGTH = 100;

        private User() {
        }
    }

    private ValidationConstants() {
    }

}
