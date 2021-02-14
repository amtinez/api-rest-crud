package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.daos.UserDao;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public Optional<UserModel> findUser(final Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<UserModel> findAllUsers() {
        return userDao.findAll();
    }

    @Override
    public UserModel saveUser(final UserModel userModel) {
        return userDao.save(userModel);
    }

    @Override
    public void deleteUser(final Long id) {
        userDao.deleteById(id);
    }

    @Override
    public boolean existsUserEmail(final String email) {
        return userDao.existsByEmail(email);
    }

    @Override
    public int updateUserEnabledStatus(final Long id, final Boolean enabled) {
        return userDao.updateEnabledStatusById(id, enabled);
    }

    @Override
    public int updateUserLockedInformation(final Long id,
                                           final String lockedBy,
                                           final LocalDateTime lockedDate,
                                           final String lockedReason) {
        return userDao.updateLockedInformationById(id, lockedBy, lockedDate, lockedReason);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final Optional<UserModel> user = Optional.ofNullable(userDao.findByEmail(email));
        return user.orElseThrow(() -> new UsernameNotFoundException(String.format("User with the email: %s not found", email)));
    }

}
