package com.amtinez.api.rest.crud.mappers;


import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static com.amtinez.api.rest.crud.constants.MapperConstants.SPRING_COMPONENT_MODEL;
import static com.amtinez.api.rest.crud.constants.MapperConstants.User.PASSWORD_PROPERTY;

/**
 * @author amartinezcerro@gmail.com
 */
@Mapper(componentModel = SPRING_COMPONENT_MODEL)
public interface UserMapper {

    @Mapping(target = PASSWORD_PROPERTY, ignore = true)
    User userModelToUser(final UserModel userModel);

    UserModel userToUserModel(final User user);

}
