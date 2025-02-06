package com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Orders;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.OrdersEntity;

//We map the entity classes to the DTOs
@Mapper(componentModel = "spring")
public interface OrdersMapper {
    @Mappings({
        @Mapping(source = "orderId", target = "orderIdDomain"),
        @Mapping(source = "suppliersId", target = "suppliersIdDomain"),
        @Mapping(source = "dateOrder", target = "dateOrderDomain"),
        @Mapping(source = "quantityProducts", target = "quantityProductsDomain"),
        @Mapping(source = "productsOrder", target = "productsOrderDomain"),
        @Mapping(source = "stateOrder", target = "stateOrderDomain"),
    })
    Orders toOrder(OrdersEntity ordersEntity);
    List<Orders> toOrders(List<OrdersEntity> ordersEntities);

    @InheritInverseConfiguration
    @Mapping(target = "suppliersEntity", ignore = true)
    OrdersEntity toOrdersEntity(Orders orders);
}
