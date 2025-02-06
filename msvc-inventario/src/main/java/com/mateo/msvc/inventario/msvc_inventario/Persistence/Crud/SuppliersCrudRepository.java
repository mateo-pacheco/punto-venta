package com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud;

import org.springframework.data.repository.CrudRepository;

import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.SuppliersEntity;


////We inherit the methods from JPA CrudRepository
public interface SuppliersCrudRepository extends CrudRepository<SuppliersEntity, String>{
}
