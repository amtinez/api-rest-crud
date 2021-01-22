package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import com.amtinez.api.rest.crud.models.UserModel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

import static com.amtinez.api.rest.crud.constants.MapperConstants.Authority.AUTHORITIES_PROPERTY;
import static com.amtinez.api.rest.crud.constants.MapperConstants.Authority.USER_MODEL_TO_USER_WITHOUT_AUTHORITIES;
import static com.amtinez.api.rest.crud.constants.MapperConstants.Authority.USER_TO_USER_MODEL_WITHOUT_AUTHORITIES;
import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;

/**
 * @author amartinezcerro@gmail.com
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper {

    Authority authorityModelToAuthority(final AuthorityModel authorityModel);

    @IterableMapping(qualifiedByName = USER_MODEL_TO_USER_WITHOUT_AUTHORITIES)
    Set<User> userModelsToUsersWithoutAuthorities(final Set<UserModel> userModels);

    @Named(USER_MODEL_TO_USER_WITHOUT_AUTHORITIES)
    @Mapping(target = AUTHORITIES_PROPERTY, ignore = true)
    User userModelToUserWithoutAuthorities(final UserModel userModel);

    AuthorityModel authorityToAuthorityModel(final Authority authority);

    @IterableMapping(qualifiedByName = USER_TO_USER_MODEL_WITHOUT_AUTHORITIES)
    Set<UserModel> userToUserModelsWithoutAuthorities(final Set<User> users);

    @Named(USER_TO_USER_MODEL_WITHOUT_AUTHORITIES)
    @Mapping(target = AUTHORITIES_PROPERTY, ignore = true)
    UserModel userToUserModelWithoutAuthorities(final User user);

}
