package com.mateo.springcloud.msvc.users.msvc_users.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.UserDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
        @Mapping(source = "id", target = "idDomain"),
        @Mapping(source = "userName", target = "userNameDomain"),
        @Mapping(source = "password", target = "passwordDomain"),
        @Mapping(source = "enabled", target = "enabledDomain"),
        @Mapping(source = "admin", target = "adminDomain"),
        @Mapping(source = "email", target = "emailDomain"),
        @Mapping(source = "roles", target = "roleDomain")
    })
    UserDomain userToUserDomain(User user);
    List<UserDomain> usersToUserDomains(List<User> users);
    @InheritInverseConfiguration
    User userDomainToUser(UserDomain userDomain);
}
