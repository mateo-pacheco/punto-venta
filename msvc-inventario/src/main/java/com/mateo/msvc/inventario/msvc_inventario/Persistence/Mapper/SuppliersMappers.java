package com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Suppliers;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.SuppliersEntity;

//We map the entity classes to the DTOs
@Mapper(componentModel = "spring", uses = { ProductsMapper.class, OrdersMapper.class })
public interface SuppliersMappers {
    @Mappings({
            @Mapping(source = "suppliersId", target = "suppliersIdDomain"),
            @Mapping(source = "nameSuppliers", target = "nameSuppliersDomain"),
            @Mapping(source = "emailSuppliers", target = "emailSuppliersDomain"),
            @Mapping(source = "numberSuppliers", target = "numberSuppliersDomain"),
            @Mapping(source = "addressSuppliers", target = "addressSuppliersDomain"),
            @Mapping(source = "productsEntities", target = "productsEntitiesDomain"),
            @Mapping(source = "ordersEntity", target = "ordersEntityDomain")
    })
    Suppliers toSupplier(SuppliersEntity suppliersEntity);

    List<Suppliers> toSuppliers(List<SuppliersEntity> suppliersEntities);

    @InheritInverseConfiguration
    SuppliersEntity toSuppliersEntity(Suppliers suppliers);
}
