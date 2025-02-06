package com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.OrdersEntity;

//We inherit the methods from JPA CrudRepository
public interface OrderCrudRepository extends CrudRepository<OrdersEntity, Integer>{
    List<OrdersEntity> findAllBySuppliersId(String suppliersId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM orders WHERE suppliers_id = :supplierId", nativeQuery = true)
    void deleteOrderBySupplierId(@Param("supplierId") String supplierId);
}
