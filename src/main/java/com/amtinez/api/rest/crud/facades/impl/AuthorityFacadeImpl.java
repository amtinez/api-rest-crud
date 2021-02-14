package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.dtos.Authority;
import com.amtinez.api.rest.crud.facades.AuthorityFacade;
import com.amtinez.api.rest.crud.mappers.AuthorityMapper;
import com.amtinez.api.rest.crud.services.AuthorityService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Component
public class AuthorityFacadeImpl implements AuthorityFacade {

    @Resource
    private AuthorityMapper authorityMapper;

    @Resource
    private AuthorityService authorityService;

    @Override
    public Optional<Authority> findAuthority(final Long id) {
        return authorityService.findAuthority(id).map(model -> authorityMapper.authorityModelToAuthority(model));
    }

    @Override
    public List<Authority> findAllAuthorities() {
        return authorityService.findAllAuthorities().stream().map(authorityMapper::authorityModelToAuthority).collect(Collectors.toList());
    }

    @Override
    public Authority saveAuthority(final Authority authority) {
        return authorityMapper.authorityModelToAuthority(authorityService.saveAuthority(authorityMapper.authorityToAuthorityModel(authority)));
    }

    @Override
    public void deleteAuthority(final Long id) {
        authorityService.deleteAuthority(id);
    }

}
