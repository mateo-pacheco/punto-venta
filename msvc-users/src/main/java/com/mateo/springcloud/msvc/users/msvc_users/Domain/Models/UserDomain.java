package com.mateo.springcloud.msvc.users.msvc_users.Domain.Models;

import java.util.List;

import com.mateo.springcloud.msvc.users.msvc_users.Persistence.Entity.Roles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDomain {
    private Long idDomain;
    private String userNameDomain;
    private String passwordDomain;
    private Boolean enabledDomain;
    private boolean adminDomain;
    private String emailDomain;
    private List<Roles> roleDomain;
}
