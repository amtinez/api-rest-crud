package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Role;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.models.UserModel;
import org.mapstruct.Context;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.CURRENT_LOCAL_DATE_TIME;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.DISABLE_USER_BY_DEFAULT;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.ENABLED_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.ENCRYPT_PASSWORD;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.LAST_ACCESS_DATE_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.LAST_PASSWORD_UPDATE_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.PASSWORD_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.ROLE_MODEL_TO_ROLE_WITHOUT_USERS;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.ROLE_TO_ROLE_MODEL_WITHOUT_USERS;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.USERS_PROPERTY;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        imports = LocalDateTime.class)
public interface UserMapper {

    @Mapping(target = PASSWORD_PROPERTY, ignore = true)
    User userModelToUser(final UserModel userModel);

    @IterableMapping(qualifiedByName = ROLE_MODEL_TO_ROLE_WITHOUT_USERS)
    Set<Role> roleModelsToRolesWithoutUsers(final Set<RoleModel> roleModels);

    @Named(ROLE_MODEL_TO_ROLE_WITHOUT_USERS)
    @Mapping(target = USERS_PROPERTY, ignore = true)
    Role roleModelToRoleWithoutUsers(final RoleModel roleModel);

    UserModel userToUserModel(final User user);

    @IterableMapping(qualifiedByName = ROLE_TO_ROLE_MODEL_WITHOUT_USERS)
    Set<RoleModel> rolesToRoleModelsWithoutUsers(final Set<Role> roles);

    @Named(ROLE_TO_ROLE_MODEL_WITHOUT_USERS)
    @Mapping(target = USERS_PROPERTY, ignore = true)
    RoleModel roleToRoleModelWithoutUsers(final Role role);

    @Mapping(target = ENABLED_PROPERTY, expression = DISABLE_USER_BY_DEFAULT)
    @Mapping(target = LAST_ACCESS_DATE_PROPERTY, expression = CURRENT_LOCAL_DATE_TIME)
    @Mapping(target = LAST_PASSWORD_UPDATE_PROPERTY, expression = CURRENT_LOCAL_DATE_TIME)
    @Mapping(target = PASSWORD_PROPERTY, source = PASSWORD_PROPERTY, qualifiedByName = ENCRYPT_PASSWORD)
    UserModel userToUserModelRegisterStep(final User user, @Context final PasswordEncoder passwordEncoder);

    @Named(ENCRYPT_PASSWORD)
    static String encryptPassword(final String password, @Context final PasswordEncoder passwordEncoder) {
        return passwordEncoder != null ? passwordEncoder.encode(password) : password;
    }

}
