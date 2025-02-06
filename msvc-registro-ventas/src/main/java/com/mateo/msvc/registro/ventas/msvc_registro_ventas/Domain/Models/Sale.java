package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//We create a DTO of the entity class
public class Sale {
    private Integer saleIdDomain;
    private String nameProductsDomain;
    private String productIdDomain;
    private Integer quantityProductsDomain;
    private Double unitPriceDomain;
    private Double totalPriceDomain;
    private String clientNameDomain;
    private String clientIdDomain;
    private String contactClientDomain;
    private LocalDateTime dateSaleDomain;
}
