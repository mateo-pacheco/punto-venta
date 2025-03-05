package com.mateo.springcloud.msvc.users.msvc_users.Domain.Interfaces;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.RolesDomain;

public interface RolesI {
    public RolesDomain getRoleByName(String name);
}
