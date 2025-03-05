package com.mateo.msvc.punto.venta.msvc_punto_venta.Models;

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
public class Product {
    private String productIdDomain;
    private String suppliersIdDomain;
    private String categoryIdDomain;
    private String nameProductDomain;
    private String descriptionProductDomain;
    private Double priceProductDomain;
    private Integer stockProductDomain;
}
