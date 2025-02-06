package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Models.Sale;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Entity.SaleEntity;

//We map the entity classes to the DTOs
@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mappings({
        @Mapping(source = "saleId", target = "saleIdDomain"),
        @Mapping(source = "nameProducts", target = "nameProductsDomain"),
        @Mapping(source = "productId", target = "productIdDomain"),
        @Mapping(source = "quantityProducts", target = "quantityProductsDomain"),
        @Mapping(source = "unitPrice", target = "unitPriceDomain"),
        @Mapping(source = "totalPrice", target = "totalPriceDomain"),
        @Mapping(source = "clientName", target = "clientNameDomain"),
        @Mapping(source = "clientId", target = "clientIdDomain"),
        @Mapping(source = "contactClient", target = "contactClientDomain"),
        @Mapping(source = "dateSale", target = "dateSaleDomain")
    })
    Sale toSale(SaleEntity saleEntity);
    List<Sale> toSales(List<SaleEntity> saleEntities);
    @InheritInverseConfiguration
    SaleEntity toSaleEntity(Sale sale);
}
