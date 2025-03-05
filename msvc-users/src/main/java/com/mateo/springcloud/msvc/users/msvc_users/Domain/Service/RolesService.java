package com.mateo.springcloud.msvc.users.msvc_users.Domain.Service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Interfaces.RolesI;
import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.RolesDomain;

@Service
public class RolesService {
    @Autowired
    private RolesI rolesI;

    @Transactional(readOnly = true)
    public RolesDomain getRoleByName(String name) {
        return rolesI.getRoleByName(name);
    }
}
