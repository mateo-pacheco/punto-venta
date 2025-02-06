package com.mateo.msvc.inventario.msvc_inventario.Domain.Repository;

import java.util.List;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Suppliers;

//Interface containing the methods that are implemented in the persistence layer
public interface SuppliersRepositoryDomain {
    public List<Suppliers> getAll();
    public Suppliers getBySuppliersId(String suppliersId);
    public Suppliers save(Suppliers suppliers);
    public void delete(String suppliersId);
    public boolean exist(String suppliersId);
}
