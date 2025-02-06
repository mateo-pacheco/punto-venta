package com.mateo.msvc.inventario.msvc_inventario.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//We create a DTO of the entity class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    private String productIdDomain;
    private String suppliersIdDomain;
    private String categoryIdDomain;
    private String nameProductDomain;
    private String descriptionProductDomain;
    private Double priceProductDomain;
    private Integer stockProductDomain;
}
