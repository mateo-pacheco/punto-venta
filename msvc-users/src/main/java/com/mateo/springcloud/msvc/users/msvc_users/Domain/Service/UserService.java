package com.mateo.springcloud.msvc.users.msvc_users.Domain.Service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Interfaces.UserI;
import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.RolesDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.UserDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.Roles;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Mapper.RolesMapper;

@Service
public class UserService {
    @Autowired
    private UserI userI;
    @Autowired
    private RolesService rolesService;
    @Autowired
    private RolesMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDomain getById(Long id) {
        return userI.getById(id);
    }

    @Transactional(readOnly = true)
    public UserDomain getByUserName(String userName) {
        return userI.getByUserName(userName);
    }

    @Transactional(readOnly = true)
    public List<UserDomain> getAll() {
        return userI.getAll();
    }

    @Transactional
    public UserDomain save(UserDomain userDomain) {
        if (userDomain.getIdDomain() == null) {
            userDomain.setPasswordDomain(passwordEncoder.encode(userDomain.getPasswordDomain()));
            userDomain.setRoleDomain(getRoles(userDomain));
            userDomain.setEnabledDomain(true);
            return userI.save(userDomain);
        } else {
            throw new IllegalStateException("The user must have an id.");
        }
    }

    @Transactional
    public UserDomain update(UserDomain userDomain) {
        if (userI.existById(userDomain.getIdDomain())) {
            userDomain.setPasswordDomain(passwordEncoder.encode(userDomain.getPasswordDomain()));
            return userI.save(userDomain);
        } else {
            throw new IllegalStateException("User does not exist.");
        }
    }

    @Transactional
    public void delete(Long id) {
        userI.delete(id);
    }

    private List<Roles> getRoles(UserDomain userDomain) {
        List<Roles> roles = new ArrayList<>();
        RolesDomain roleUser = rolesService.getRoleByName("ROLE_USER");
        Roles roleUserE = mapper.rolesDomainToRoles(roleUser);
        roles.add(roleUserE);

        if (userDomain.isAdminDomain()) {
            RolesDomain roleAdmin = rolesService.getRoleByName("ROLE_ADMIN");
            Roles roleAdminE = mapper.rolesDomainToRoles(roleAdmin);
            roles.add(roleAdminE);
        }
        return roles;
    }
}
