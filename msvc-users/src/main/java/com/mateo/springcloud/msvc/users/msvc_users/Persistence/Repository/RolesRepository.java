package com.mateo.springcloud.msvc.users.msvc_users.Persistence.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Interfaces.RolesI;
import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.RolesDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.Roles;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Mapper.RolesMapper;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.RepositoryCRUD.RolesRepositoryCRUD;

@Repository
public class RolesRepository implements RolesI{
    @Autowired
    private RolesRepositoryCRUD rolesRepositoryCRUD;
    @Autowired
    private RolesMapper rolesMapper;

    @Override
    public RolesDomain getRoleByName(String name) {
        Roles roles = rolesRepositoryCRUD.findAllByName(name);
        return rolesMapper.rolesToRolesDomain(roles);
    }
}
