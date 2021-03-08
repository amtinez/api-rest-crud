package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.dtos.User;
import com.amtinez.api.rest.crud.facades.UserFacade;
import com.amtinez.api.rest.crud.mappers.UserMapper;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.services.UserService;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Component
public class UserFacadeImpl implements UserFacade {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuditorAware<String> auditorAware;

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
        final UserModel userModel = userMapper.userToUserModelRegisterStep(user, passwordEncoder);
        return userMapper.userModelToUser(userService.saveUser(userModel));
    }

    @Override
    public int enableUser(final Long id) {
        return userService.updateUserEnabledStatus(id, Boolean.TRUE);
    }

    @Override
    public int disableUser(final Long id) {
        return userService.updateUserEnabledStatus(id, Boolean.FALSE);
    }

    @Override
    public void deleteUser(final Long id) {
        userService.deleteUser(id);
    }

    @Override
    public User updateUser(final User user) {
        return userMapper.userModelToUser(userService.saveUser(userMapper.userToUserModel(user)));
    }

    @Override
    public int lockUser(final Long id, final String lockedReason) {
        final Optional<String> currentUser = auditorAware.getCurrentAuditor();
        return currentUser.map(currentUserFound -> userService.updateUserLockedInformation(id,
                                                                                           currentUserFound,
                                                                                           LocalDateTime.now(),
                                                                                           lockedReason))
                          .orElse(NumberUtils.INTEGER_ZERO);
    }

    @Override
    public int unlockUser(final Long id) {
        return userService.updateUserLockedInformation(id, null, null, null);
    }

}
