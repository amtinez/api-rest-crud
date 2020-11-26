package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import com.amtinez.api.rest.crud.models.UserModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;

import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.AUTHORITY_MODEL_TO_AUTHORITY_WITHOUT_USERS;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.AUTHORITY_TO_AUTHORITY_MODEL_WITHOUT_USERS;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.PASSWORD_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.USERS_PROPERTY;

/**
 * @author amartinezcerro@gmail.com
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL)
public interface UserMapper {

    @Mapping(target = PASSWORD_PROPERTY, ignore = true)
    User userModelToUser(final UserModel userModel);

    @IterableMapping(qualifiedByName = AUTHORITY_MODEL_TO_AUTHORITY_WITHOUT_USERS)
    Set<Authority> authorityModelsToAuthoritiesWithoutUsers(final Set<AuthorityModel> authorityModels);

    @Named(AUTHORITY_MODEL_TO_AUTHORITY_WITHOUT_USERS)
    @Mapping(target = USERS_PROPERTY, ignore = true)
    Authority authorityModelToAuthorityWithoutUsers(final AuthorityModel authorityModel);

    UserModel userToUserModel(final User user);

    @IterableMapping(qualifiedByName = AUTHORITY_TO_AUTHORITY_MODEL_WITHOUT_USERS)
    Set<AuthorityModel> authoritiesToAuthorityModelsWithoutUsers(final Set<Authority> authorities);

    @Named(AUTHORITY_TO_AUTHORITY_MODEL_WITHOUT_USERS)
    @Mapping(target = USERS_PROPERTY, ignore = true)
    AuthorityModel authorityToAuthorityModelWithoutUsers(final Authority authority);

}
