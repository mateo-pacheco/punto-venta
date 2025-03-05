package com.mateo.springcloud.msvc.users.msvc_users.Domain.Interfaces;

import java.util.List;

import com.mateo.springcloud.msvc.users.msvc_users.Domain.Models.UserDomain;

public interface UserI {
    public UserDomain getById(Long id);
    public UserDomain getByUserName(String userName);
    public List<UserDomain> getAll();
    public UserDomain save(UserDomain UserDomain);
    public void delete(Long id);
    public boolean existById(Long id);
}
