package com.amtinez.api.rest.crud.security.impl;

import com.amtinez.api.rest.crud.daos.UserDao;
import com.amtinez.api.rest.crud.mappers.UserDetailsMapper;
import com.amtinez.api.rest.crud.models.UserModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDetailsMapper userDetailsMapper;

    @Resource
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(final String email) {
        final Optional<UserModel> user = Optional.ofNullable(userDao.findByEmail(email));
        return user.map(userDetailsMapper::userModelToUserDetails)
                   .orElseThrow(() -> new UsernameNotFoundException(String.format("User with the email: %s not found", email)));
    }

}
