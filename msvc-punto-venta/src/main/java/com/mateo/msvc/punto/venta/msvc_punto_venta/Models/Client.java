package com.mateo.msvc.punto.venta.msvc_punto_venta.Models;

import java.time.LocalDateTime;

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
public class Client {
    private String clientIdDomain;
    private String nameDomain;
    private String emailDomain;
    private String phoneNumberDomain;
    private String addressDomain;
    private LocalDateTime registrationDateDomain;
}
