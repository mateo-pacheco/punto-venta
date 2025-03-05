package com.mateo.springcloud.msvc.oauth.msvc_oauth.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Users {
    private Long idDomain;
    private String userNameDomain;
    private String passwordDomain;
    private Boolean enabledDomain;
    private boolean adminDomain;
    private String emailDomain;
    private List<Roles> roleDomain;
}
