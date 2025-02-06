package com.mateo.msvc.inventario.msvc_inventario.Domain.Models;

import java.time.LocalDateTime;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//We create a DTO of the entity class
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String categoryIdDomain;
    private String nameCategoryDomain;
    private LocalDateTime creationDateDomain;
    private List<Products> productsEntitiesDomain;
}
