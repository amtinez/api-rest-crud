package com.amtinez.api.rest.crud.constants;

/**
 * @author amartinezcerro@gmail.com
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
            public static final String LOCKED_AT_FIELD = "locked_at";
            public static final String LAST_ACCESS_DATE_FIELD = "last_access_at";
            public static final String LAST_PASSWORD_UPDATE_DATE_FIELD = "last_password_update_at";
            public static final String ENABLED_FIELD = "enabled";

            private User() {
            }
        }

        public static class Authority {

            public static final String TABLE_NAME = "authorities";
            public static final String NAME_FIELD = "name";
            public static final int NAME_FIELD_LENGTH = 50;

            private Authority() {
            }
        }

        public static class UsersAuthorities {

            public static final String TABLE_NAME = "users_authorities";
            public static final String ID_AUTHORITY_FIELD = "id_authority";
            public static final String ID_USER_FIELD = "id_user";

            private UsersAuthorities() {
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
