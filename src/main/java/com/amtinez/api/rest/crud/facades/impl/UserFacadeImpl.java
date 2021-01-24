package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.facades.UserFacade;
import com.amtinez.api.rest.crud.mappers.UserMapper;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.services.UserService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @author amartinezcerro@gmail.com
 */
@Component
public class UserFacadeImpl implements UserFacade {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Override
    public Optional<User> findUser(final Long id) {
        return userService.findUser(id).map(model -> userMapper.userModelToUser(model));
    }

    @Override
    public List<User> findAllUsers() {
        return userService.findAllUsers().stream().map(userMapper::userModelToUser).collect(Collectors.toList());
    }

    @Override
    public User registerUser(final User user) {
        final UserModel userModel = userMapper.userToUserModel(user);
        userModel.setEnabled(Boolean.FALSE);
        return userMapper.userModelToUser(userService.saveUser(userModel));
    }

    @Override
    public User enableUser(final Long id) {
        final UserModel userModel = UserModel.builder()
                                             .id(id)
                                             .enabled(Boolean.TRUE)
                                             .build();
        return userMapper.userModelToUser(userService.saveUser(userModel));
    }

    @Override
    public void deleteUser(final Long id) {
        userService.deleteUser(id);
    }

    @Override
    public User updateUser(final User user) {
        //TODO: UPDATE ONLY SELECTED FIELDS
        return userMapper.userModelToUser(userService.saveUser(userMapper.userToUserModel(user)));
    }

}
