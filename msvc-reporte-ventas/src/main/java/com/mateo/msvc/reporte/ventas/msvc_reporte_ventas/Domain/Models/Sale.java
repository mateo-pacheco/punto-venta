package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
