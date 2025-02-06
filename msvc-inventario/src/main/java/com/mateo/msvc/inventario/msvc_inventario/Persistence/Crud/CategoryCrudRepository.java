package com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.CategoryEntity;

//We inherit the methods from JPA CrudRepository
public interface CategoryCrudRepository extends CrudRepository<CategoryEntity, String>{
    List<CategoryEntity> findAllByNameCategory(String nameCategory);
}
