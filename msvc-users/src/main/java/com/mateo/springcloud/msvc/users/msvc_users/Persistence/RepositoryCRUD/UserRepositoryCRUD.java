package com.mateo.springcloud.msvc.users.msvc_users.Persistence.RepositoryCRUD;

import org.springframework.data.repository.CrudRepository;

import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.User;



public interface UserRepositoryCRUD extends CrudRepository<User, Long> {
    User findByUserName(String userName);
}
