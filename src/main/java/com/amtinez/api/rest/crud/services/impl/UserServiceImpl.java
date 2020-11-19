package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.daos.UserDao;
import com.amtinez.api.rest.crud.models.UserModel;
import com.amtinez.api.rest.crud.services.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import static com.amtinez.api.rest.crud.constants.SecurityConstants.USER_EMAIL_NOT_FOUND;

/**
 * @author amartinezcerro@gmail.com
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
    public void removeUser(final Long id) {
        userDao.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final Optional<UserModel> user = Optional.ofNullable(userDao.findByEmail(email));
        return user.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_EMAIL_NOT_FOUND, email)));
    }

}