package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Role;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.models.UserModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.PASSWORD_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.ROLE_MODEL_TO_ROLE_WITHOUT_USERS;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.ROLE_TO_ROLE_MODEL_WITHOUT_USERS;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.USERS_PROPERTY;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
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

}
