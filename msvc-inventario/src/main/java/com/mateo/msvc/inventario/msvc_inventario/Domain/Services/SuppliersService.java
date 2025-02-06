package com.mateo.msvc.inventario.msvc_inventario.Domain.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Suppliers;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.SuppliersRepositoryDomain;

//Services that will be consumed from the controllers
@Service
public class SuppliersService {
    @Autowired
    private SuppliersRepositoryDomain suppliersRepositoryDomain;
    
    @Transactional(readOnly = true)
    public List<Suppliers> getAll(){
        return suppliersRepositoryDomain.getAll();
    }

    @Transactional(readOnly = true)
    public Suppliers getBySupplierId(String suppliersId){
        return suppliersRepositoryDomain.getBySuppliersId(suppliersId);
    }

    @Transactional
    public Suppliers save(Suppliers suppliers){
        if (suppliers.getSuppliersIdDomain() != null && !suppliersRepositoryDomain.exist(suppliers.getSuppliersIdDomain())) {
             return suppliersRepositoryDomain.save(suppliers);
        }else{
            throw new IllegalStateException("The supplier could not be saved because it already exists.");
        }
    }

    @Transactional
    public Suppliers update(Suppliers suppliers){
        if (suppliers.getSuppliersIdDomain() != null && suppliersRepositoryDomain.exist(suppliers.getSuppliersIdDomain())) {
            return suppliersRepositoryDomain.save(suppliers);
        }else{
            throw new IllegalStateException("The supplier could not be updated because it does not have an ID or does not exite.");
        }
    }

    @Transactional
    public void delete(String suppliersId){
        if (suppliersRepositoryDomain.exist(suppliersId)) {
            suppliersRepositoryDomain.delete(suppliersId);
        }else{
            throw new IllegalStateException("The supplier could not be eliminated since it does not exist.");
        }
    }
}
