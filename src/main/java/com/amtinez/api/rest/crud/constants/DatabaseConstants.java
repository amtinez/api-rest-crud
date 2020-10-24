package com.amtinez.api.rest.crud.constants;

/**
 * @author amartinezcerro@gmail.com
 */
public class DatabaseConstants {

    public static final String DATABASE_NAME = "api-rest-crud";

    public static class Table {

        public static class User {

            public static final String TABLE_NAME = "user";
            public static final String FIRST_NAME_FIELD = "first_name";
            public static final int FIRST_NAME_FIELD_LENGTH = 50;
            public static final String LAST_NAME_FIELD = "last_name";
            public static final int LAST_NAME_FIELD_LENGTH = 50;
            public static final String EMAIL_FIELD = "email";
            public static final int EMAIL_FIELD_LENGTH = 50;
            public static final String PASSWORD_FIELD = "password";
            public static final int PASSWORD_FIELD_LENGTH = 80;
            public static final String BIRTHDAY_DATE_FIELD = "birthday_date";
            public static final String REGISTER_DATE_FIELD = "register_date";
            public static final String DELETE_DATE_FIELD = "delete_date";
            public static final String LAST_ACCESS_DATE_FIELD = "last_access_date";
            public static final String LAST_UPDATE_DATE_FIELD = "last_update_date";
            public static final String PASSWORD_UPDATE_DATE_FIELD = "password_update_date";
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

        private Table() {
        }
    }

    private DatabaseConstants() {

    }
}
