package com.mateo.springcloud.msvc.users.msvc_users.Persistence.RepositoryCRUD;

import org.springframework.data.repository.CrudRepository;

import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.Roles;

public interface RolesRepositoryCRUD extends CrudRepository<Roles, Long> {
    Roles findAllByName(String name);
}
