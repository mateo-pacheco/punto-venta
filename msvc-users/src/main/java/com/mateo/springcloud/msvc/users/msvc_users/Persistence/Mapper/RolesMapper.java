package com.mateo.springcloud.msvc.users.msvc_users.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.RolesDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.Roles;

@Mapper(componentModel = "spring")
public interface RolesMapper {
    @Mappings({
        @Mapping(source = "id", target = "idDomain"),
        @Mapping(source = "name", target = "nameDomain")
    })
    RolesDomain rolesToRolesDomain(Roles roles);
    List<RolesDomain> rolesToRolesDomains(List<Roles> roles);
    
    @InheritInverseConfiguration
    Roles rolesDomainToRoles(RolesDomain rolesDomain);
}
