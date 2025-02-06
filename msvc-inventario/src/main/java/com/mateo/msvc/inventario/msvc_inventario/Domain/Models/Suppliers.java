package com.mateo.msvc.inventario.msvc_inventario.Domain.Models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//We create a DTO of the entity class
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Suppliers {
    private String suppliersIdDomain;
    private String nameSuppliersDomain;
    private String emailSuppliersDomain;
    private String numberSuppliersDomain;
    private String addressSuppliersDomain;
    private List<Products> productsEntitiesDomain;
    private List<Orders> ordersEntityDomain;
}
