package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Crud;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Entity.SaleEntity;

//We inherit the methods from JPA CrudRepository
public interface SaleCrudRepository extends CrudRepository<SaleEntity, Integer> {
    List<SaleEntity> findAllByProductId(String productId);
    List<SaleEntity> findAllByClientId(String clientId);
    List<SaleEntity> findAllByDateSaleBetween(LocalDate dateStart, LocalDate dateEnd);
}
