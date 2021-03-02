package com.amtinez.api.rest.crud.utils;

import com.amtinez.api.rest.crud.models.RoleModel;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
public class RoleUtils {

    /**
     * Returns the name of the role prefixed with "ROLE_"
     *
     * @param roleModel the role
     * @return the prefixed role name
     */
    public static String getPrefixedName(final RoleModel roleModel) {
        return String.format("ROLE_%s", roleModel.getName());
    }

    private RoleUtils() {
    }

}
