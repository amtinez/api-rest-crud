package com.amtinez.api.rest.crud.mappers;

import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.models.UserModel;
import org.mapstruct.Mapper;

/**
 * @author amartinezcerro@gmail.com
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    User userModelToUser(final UserModel userModel);

    UserModel userToUserModel(final User user);

}
