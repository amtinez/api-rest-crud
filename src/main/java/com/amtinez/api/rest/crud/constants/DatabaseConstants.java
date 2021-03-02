package com.amtinez.api.rest.crud.constants;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class DatabaseConstants {

    public static class Table {

        public static class User {

            public static final String TABLE_NAME = "users";
            public static final String FIRST_NAME_FIELD = "first_name";
            public static final int FIRST_NAME_FIELD_LENGTH = 50;
            public static final String LAST_NAME_FIELD = "last_name";
            public static final int LAST_NAME_FIELD_LENGTH = 50;
            public static final String EMAIL_FIELD = "email";
            public static final int EMAIL_FIELD_LENGTH = 50;
            public static final String PASSWORD_FIELD = "password";
            public static final int PASSWORD_FIELD_LENGTH = 80;
            public static final String BIRTHDAY_DATE_FIELD = "birthday_date";
            public static final String LOCKED_BY_FIELD = "locked_by";
            public static final int LOCKED_BY_FIELD_LENGTH = 100;
            public static final String LOCKED_AT_FIELD = "locked_at";
            public static final String LOCKED_REASON_FIELD = "locked_reason";
            public static final int LOCKED_REASON_FIELD_LENGTH = 100;
            public static final String LAST_ACCESS_DATE_FIELD = "last_access_at";
            public static final String LAST_PASSWORD_UPDATE_DATE_FIELD = "last_password_update_at";
            public static final String ENABLED_FIELD = "enabled";

            private User() {
            }
        }

        public static class Role {

            public static final String TABLE_NAME = "roles";
            public static final String NAME_FIELD = "name";
            public static final int NAME_FIELD_LENGTH = 50;

            private Role() {
            }
        }

        public static class UsersRoles {

            public static final String TABLE_NAME = "users_roles";
            public static final String ID_ROLE_FIELD = "id_role";
            public static final String ID_USER_FIELD = "id_user";

            private UsersRoles() {
            }
        }

        public static class Auditable {

            public static final String CREATED_BY_FIELD = "created_by";
            public static final String CREATED_AT_FIELD = "created_at";
            public static final String LAST_UPDATED_BY_FIELD = "last_updated_by";
            public static final String LAST_UPDATED_AT_FIELD = "last_updated_at";

            private Auditable() {
            }
        }

        private Table() {
        }
    }

    private DatabaseConstants() {
    }

}
