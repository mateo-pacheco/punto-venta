package com.mateo.msvc.inventario.msvc_inventario.Domain.Repository;

import java.util.List;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Orders;

//Interface containing the methods that are implemented in the persistence layer
public interface OrdersRepositoryDomain {
    public List<Orders> getAll();
    public Orders getById(Integer ordersId);
    public List<Orders> getAllBySuppliersId(String suppliersId);
    public Orders save(Orders orders);
    public void delete(Integer orderId);
    public boolean exist(Integer ordersId);
    public void deleteOrderBySupplierId(String supplierId);
}
