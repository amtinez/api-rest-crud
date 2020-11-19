package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.daos.AuthorityDao;
import com.amtinez.api.rest.crud.models.AuthorityModel;
import com.amtinez.api.rest.crud.services.AuthorityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

/**
 * @author amartinezcerro@gmail.com
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Resource
    private AuthorityDao authorityDao;

    @Override
    public Optional<AuthorityModel> findAuthority(final Long id) {
        return authorityDao.findById(id);
    }

    @Override
    public List<AuthorityModel> findAllAuthorities() {
        return authorityDao.findAll();
    }

    @Override
    public AuthorityModel saveAuthority(final AuthorityModel authorityModel) {
        return authorityDao.save(authorityModel);
    }

    @Override
    public void removeAuthority(final Long id) {
        authorityDao.deleteById(id);
    }

}
