package com.mateo.springcloud.msvc.users.msvc_users.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Interfaces.UserI;
import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.UserDomain;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.User;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Mapper.UserMapper;
import com.mateo.springcloud.msvc.users.msvc_users.Persistence.RepositoryCRUD.UserRepositoryCRUD;

@Repository
public class UserRepository implements UserI{
    @Autowired
    private UserRepositoryCRUD userRepositoryCRUD;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDomain getById(Long id) {
        User user = userRepositoryCRUD.findById(id).orElseThrow();
        return userMapper.userToUserDomain(user);
    }

    @Override
    public UserDomain getByUserName(String userName) {
        User user = userRepositoryCRUD.findByUserName(userName);
        return userMapper.userToUserDomain(user);
    }

    @Override
    public List<UserDomain> getAll() {
        List<User> users = (List<User>) userRepositoryCRUD.findAll();
        return userMapper.usersToUserDomains(users);
    }

    @Override
    public UserDomain save(UserDomain UserDomain) {
        User user = userMapper.userDomainToUser(UserDomain);
        user = userRepositoryCRUD.save(user);
        return userMapper.userToUserDomain(user);
    }

    @Override
    public void delete(Long id) {
        userRepositoryCRUD.deleteById(id);
    }

    @Override
    public boolean existById(Long id) {
        return userRepositoryCRUD.existsById(id);
    }
}
