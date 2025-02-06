package com.mateo.msvc.inventario.msvc_inventario.Domain.Models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//We create a DTO of the entity class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    private Integer orderIdDomain;
    private String suppliersIdDomain;
    private LocalDateTime dateOrderDomain;
    private Integer quantityProductsDomain;
    private String productsOrderDomain;
    private Boolean stateOrderDomain;
}
