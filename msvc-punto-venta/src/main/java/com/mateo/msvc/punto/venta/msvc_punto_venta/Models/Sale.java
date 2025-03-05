package com.mateo.msvc.punto.venta.msvc_punto_venta.Models;

import java.time.LocalDateTime;

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
