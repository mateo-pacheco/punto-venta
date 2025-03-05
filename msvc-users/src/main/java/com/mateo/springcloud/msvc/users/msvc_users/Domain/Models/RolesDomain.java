package com.mateo.springcloud.msvc.users.msvc_users.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RolesDomain {
    private Long idDomain;
    private String nameDomain;
}
