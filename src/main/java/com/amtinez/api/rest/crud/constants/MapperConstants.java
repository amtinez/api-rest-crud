package com.amtinez.api.rest.crud.constants;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class MapperConstants {

    public static final String SPRING_COMPONENT_MODEL = "spring";

    public static class User {

        public static final String USERS_PROPERTY = "users";
        public static final String PASSWORD_PROPERTY = "password";
        public static final String ENABLED_PROPERTY = "enabled";
        public static final String LAST_ACCESS_DATE_PROPERTY = "lastAccessDate";
        public static final String LAST_PASSWORD_UPDATE_PROPERTY = "lastPasswordUpdateDate";

        public static final String ENCRYPT_PASSWORD = "encryptPassword";
        public static final String ROLE_MODEL_TO_ROLE_WITHOUT_USERS = "roleModelToRoleWithoutUsers";
        public static final String ROLE_TO_ROLE_MODEL_WITHOUT_USERS = "roleToRoleModelWithoutUsers";

        public static final String DISABLE_USER_BY_DEFAULT = "java(Boolean.FALSE)";
        public static final String CURRENT_LOCAL_DATE_TIME = "java(LocalDateTime.now())";

        private User() {
        }
    }

    public static class Role {

        public static final String ROLES_PROPERTY = "roles";

        public static final String USER_MODEL_TO_USER_WITHOUT_ROLES = "userModelToUserWithoutRoles";
        public static final String USER_TO_USER_MODEL_WITHOUT_ROLES = "userToUserModelWithoutRoles";

        private Role() {
        }
    }

    public static class UserDetails {

        public static final String AUTHORITIES_PROPERTY = "authorities";
        public static final String ACCOUNT_NOT_LOCKED_PROPERTY = "accountNonLocked";
        public static final String ACCOUNT_NOT_EXPIRED_PROPERTY = "accountNonExpired";
        public static final String CREDENTIALS_NOT_EXPIRED_PROPERTY = "credentialsNonExpired";
        public static final String ROLES_PROPERTY = "roles";
        public static final String LAST_ACCESS_DATE_PROPERTY = "lastAccessDate";
        public static final String LOCKED_DATE_PROPERTY = "lockedDate";
        public static final String LAST_PASSWORD_UPDATE_PROPERTY = "lastPasswordUpdateDate";

        public static final String ROLES_TO_AUTHORITIES = "rolesToAuthorities";
        public static final String IS_ACCOUNT_NOT_LOCKED = "isAccountNonLocked";
        public static final String IS_ACCOUNT_NOT_EXPIRED = "isAccountNonExpired";
        public static final String IS_CREDENTIALS_NOT_EXPIRED = "isCredentialsNonExpired";

        private UserDetails() {
        }
    }

    private MapperConstants() {
    }

}
