package com.mateo.msvc.inventario.msvc_inventario.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Orders;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.OrdersRepositoryDomain;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud.OrderCrudRepository;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.OrdersEntity;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper.OrdersMapper;

import jakarta.persistence.EntityNotFoundException;

//Repository that accesses data from the database and transforms it into DTOs
@Repository
public class OrdersRepository implements OrdersRepositoryDomain {
    @Autowired
    private OrderCrudRepository orderCrudRepository;
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> getAll() {
        List<OrdersEntity> ordersEntities = (List<OrdersEntity>) orderCrudRepository.findAll();
        return ordersMapper.toOrders(ordersEntities);
    }

    @Override
    public Orders getById(Integer ordersId) {
        OrdersEntity ordersEntity = orderCrudRepository.findById(ordersId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + ordersId));
        return ordersMapper.toOrder(ordersEntity);
    }

    @Override
    public List<Orders> getAllBySuppliersId(String suppliersId) {
        List<OrdersEntity> ordersEntities = orderCrudRepository.findAllBySuppliersId(suppliersId);
        return ordersMapper.toOrders(ordersEntities);
    }

    @Override
    public Orders save(Orders orders){
        OrdersEntity ordersEntity = ordersMapper.toOrdersEntity(orders);
        orderCrudRepository.save(ordersEntity);
        return ordersMapper.toOrder(ordersEntity);
    }

    @Override
    public void delete(Integer orderId){
        orderCrudRepository.deleteById(orderId);
    }

    @Override
    public boolean exist(Integer ordersId){
        return orderCrudRepository.existsById(ordersId);
    }

    @Override
    public void deleteOrderBySupplierId(String supplierId){
        orderCrudRepository.deleteOrderBySupplierId(supplierId);
    }
}
