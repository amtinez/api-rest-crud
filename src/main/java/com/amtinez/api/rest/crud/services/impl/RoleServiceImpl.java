package com.amtinez.api.rest.crud.services.impl;

import com.amtinez.api.rest.crud.daos.RoleDao;
import com.amtinez.api.rest.crud.models.RoleModel;
import com.amtinez.api.rest.crud.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleDao roleDao;

    @Override
    public Optional<RoleModel> findRole(final Long id) {
        return roleDao.findById(id);
    }

    @Override
    public List<RoleModel> findAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public RoleModel saveRole(final RoleModel roleModel) {
        return roleDao.save(roleModel);
    }

    @Override
    public void deleteRole(final Long id) {
        roleDao.deleteById(id);
    }

}
