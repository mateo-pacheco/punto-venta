package com.mateo.msvc.inventario.msvc_inventario.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Suppliers;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.SuppliersRepositoryDomain;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud.SuppliersCrudRepository;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.SuppliersEntity;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper.SuppliersMappers;

import jakarta.persistence.EntityNotFoundException;

//Repository that accesses data from the database and transforms it into DTOs
@Repository
public class SuppliersRepository implements SuppliersRepositoryDomain{
    @Autowired
    private SuppliersCrudRepository suppliersCrudRepository;
    @Autowired
    private SuppliersMappers suppliersMappers;

    @Override
    public List<Suppliers> getAll(){
        List<SuppliersEntity> suppliersEntities = (List<SuppliersEntity>) suppliersCrudRepository.findAll();
        return suppliersMappers.toSuppliers(suppliersEntities);
    }

    @Override
    public Suppliers getBySuppliersId(String suppliersId){
        SuppliersEntity suppliersEntity = suppliersCrudRepository.findById(suppliersId).orElseThrow(
            () -> new EntityNotFoundException("Suppliers not found with id: " + suppliersId)
        );
        return suppliersMappers.toSupplier(suppliersEntity);
    }

    @Override
    public Suppliers save(Suppliers suppliers){
        SuppliersEntity suppliersEntity = suppliersMappers.toSuppliersEntity(suppliers);
        suppliersCrudRepository.save(suppliersEntity);
        return suppliersMappers.toSupplier(suppliersEntity);
    }

    @Override
    public void delete(String suppliersId){
        suppliersCrudRepository.deleteById(suppliersId);
    }

    @Override
    public boolean exist(String suppliersId){
        return suppliersCrudRepository.existsById(suppliersId);
    }
}
