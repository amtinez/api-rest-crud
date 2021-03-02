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

import static com.amtinez.api.rest.crud.constants.MapperConstants.Role.ROLES_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.Role.USER_MODEL_TO_USER_WITHOUT_ROLES;
import static com.amtinez.api.rest.crud.constants.MapperConstants.Role.USER_TO_USER_MODEL_WITHOUT_ROLES;
import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    Role roleModelToRole(final RoleModel roleModel);

    @IterableMapping(qualifiedByName = USER_MODEL_TO_USER_WITHOUT_ROLES)
    Set<User> userModelsToUsersWithoutRoles(final Set<UserModel> userModels);

    @Named(USER_MODEL_TO_USER_WITHOUT_ROLES)
    @Mapping(target = ROLES_PROPERTY, ignore = true)
    User userModelToUserWithoutRoles(final UserModel userModel);

    RoleModel roleToRoleModel(final Role role);

    @IterableMapping(qualifiedByName = USER_TO_USER_MODEL_WITHOUT_ROLES)
    Set<UserModel> userToUserModelsWithoutRoles(final Set<User> users);

    @Named(USER_TO_USER_MODEL_WITHOUT_ROLES)
    @Mapping(target = ROLES_PROPERTY, ignore = true)
    UserModel userToUserModelWithoutRoles(final User user);

}
