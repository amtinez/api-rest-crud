package com.amtinez.api.rest.crud.constants;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class MapperConstants {

    public static final String SPRING_COMPONENT_MODEL = "spring";

    public static class User {

        public static final String PASSWORD_PROPERTY = "password";
        public static final String USERS_PROPERTY = "users";

        public static final String AUTHORITY_MODEL_TO_AUTHORITY_WITHOUT_USERS = "authorityModelToAuthorityWithoutUsers";
        public static final String AUTHORITY_TO_AUTHORITY_MODEL_WITHOUT_USERS = "authorityToAuthorityModelWithoutUsers";

        private User() {
        }
    }

    public static class Authority {

        public static final String AUTHORITIES_PROPERTY = "authorities";

        public static final String USER_MODEL_TO_USER_WITHOUT_AUTHORITIES = "userModelToUserWithoutAuthorities";
        public static final String USER_TO_USER_MODEL_WITHOUT_AUTHORITIES = "userToUserModelWithoutAuthorities";

        private Authority() {
        }
    }

    private MapperConstants() {
    }

}
