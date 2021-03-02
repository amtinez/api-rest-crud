package com.amtinez.api.rest.crud.facades.impl;

import com.amtinez.api.rest.crud.dtos.Role;
import com.amtinez.api.rest.crud.facades.RoleFacade;
import com.amtinez.api.rest.crud.mappers.RoleMapper;
import com.amtinez.api.rest.crud.services.RoleService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

/**
 * @author Alejandro Martínez Cerro <amartinezcerro @ gmail.com>
 */
@Component
public class RoleFacadeImpl implements RoleFacade {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private RoleService roleService;

    @Override
    public Optional<Role> findRole(final Long id) {
        return roleService.findRole(id).map(model -> roleMapper.roleModelToRole(model));
    }

    @Override
    public List<Role> findAllRoles() {
        return roleService.findAllRoles().stream().map(roleMapper::roleModelToRole).collect(Collectors.toList());
    }

    @Override
    public Role saveRole(final Role role) {
        return roleMapper.roleModelToRole(roleService.saveRole(roleMapper.roleToRoleModel(role)));
    }

    @Override
    public void deleteRole(final Long id) {
        roleService.deleteRole(id);
    }

}
