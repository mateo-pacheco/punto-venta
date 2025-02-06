package com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Products;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.ProductsEntity;

//We map the entity classes to the DTOs
@Mapper(componentModel = "spring")
public interface ProductsMapper {
    @Mappings({
        @Mapping(source = "productId", target = "productIdDomain"),
        @Mapping(source = "suppliersId", target = "suppliersIdDomain"),
        @Mapping(source = "categoryId", target = "categoryIdDomain"),
        @Mapping(source = "nameProduct", target = "nameProductDomain"),
        @Mapping(source = "descriptionProduct", target = "descriptionProductDomain"),
        @Mapping(source = "priceProduct", target = "priceProductDomain"),
        @Mapping(source = "stockProduct", target = "stockProductDomain")
    })
    Products toProduct(ProductsEntity productsEntity);
    List<Products> toProducts(List<ProductsEntity> productsEntities);

    @InheritInverseConfiguration
    @Mapping(target = "categoryEntity", ignore = true)
    @Mapping(target = "suppliersEntity", ignore = true)
    ProductsEntity toProductsEntity(Products products);
}
