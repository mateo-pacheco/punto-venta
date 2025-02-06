package com.mateo.msvc.inventario.msvc_inventario.Domain.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Orders;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Products;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.OrdersRepositoryDomain;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.ProductsRepositoryDomain;

//Services that will be consumed from the controllers
@Service
public class OrdersService {
    @Autowired
    private OrdersRepositoryDomain ordersRepositoryDomain;
    @Autowired
    private ProductsRepositoryDomain productsRepositoryDomain;

    @Transactional(readOnly = true)
    public List<Orders> getAll() {
        return ordersRepositoryDomain.getAll();
    }

    @Transactional(readOnly = true)
    public Orders getById(Integer ordersId) {
        return ordersRepositoryDomain.getById(ordersId);
    }

    @Transactional(readOnly = true)
    public List<Orders> getAllBySuppliersId(String suppliersId) {
        return ordersRepositoryDomain.getAllBySuppliersId(suppliersId);
    }

    @Transactional
    public Orders save(Orders orders, Integer stock) {
        if (orders.getOrderIdDomain() == null) {

            String productsOrder = getNamesProducts(stock, orders.getSuppliersIdDomain());

            orders.setProductsOrderDomain(productsOrder);

            return ordersRepositoryDomain.save(orders);
        } else {
            throw new IllegalStateException("The order could not be saved because it already exists.");
        }
    }

    @Transactional
    public Orders update(Orders orders, Integer stock) {

        if (orders.getOrderIdDomain() == null || !ordersRepositoryDomain.exist(orders.getOrderIdDomain())) {
            throw new IllegalStateException(
                    "The order could not be updated because it does not have an ID or does not exite.");
        }

        String productsOrder = getNamesProducts(stock, orders.getSuppliersIdDomain());

        orders.setProductsOrderDomain(null);
        orders.setProductsOrderDomain(productsOrder);

        if (orders.getStateOrderDomain() == true) {
            productsRepositoryDomain.updateStock(orders.getSuppliersIdDomain(), stock,
                    orders.getQuantityProductsDomain());
        }

        return ordersRepositoryDomain.save(orders);
    }

    @Transactional
    public void delete(Integer ordersId) {
        if (ordersRepositoryDomain.exist(ordersId)) {
            ordersRepositoryDomain.delete(ordersId);
        } else {
            throw new IllegalStateException("The order could not be eliminated since it does not exist.");
        }
    }

    @Transactional(readOnly = true)
    public String getNamesProducts(Integer stock, String suppliersId) {
        List<Products> products = productsRepositoryDomain.getAllByStockAndSuppliers(stock,
                suppliersId);
        String productsOrder = products.stream()
                .map(Products::getNameProductDomain)
                .collect(Collectors.joining(", "));

        return productsOrder;
    }

    @Transactional
    public void deleteOrderBySupplierId(String supplierId){
        ordersRepositoryDomain.deleteOrderBySupplierId(supplierId);
    }
}
