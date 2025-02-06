package com.mateo.msvc.inventario.msvc_inventario.Domain.Repository;

import java.util.List;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Products;

//Interface containing the methods that are implemented in the persistence layer
public interface ProductsRepositoryDomain {
    public List<Products> getAll();
    public Products getByProductId(String productsId);
    public List<Products> getAllByNameProduct(String nameProduct);
    public List<Products> getAllByCategoryId(String categoryId);
    public List<Products> getAllByStockProduct(Integer stockProduct);
    public List<Products> getAllBySuppliersId(String suppliersId);
    public Products save(Products products);
    public void delete(String productsId);
    public boolean exist(String productsId);
    public List<Products> getAllByStockAndSuppliers(Integer stockProduct, String suppliersId);
    public void updateStock(String suppliersId, Integer MinimumStock, Integer newStock);
    public Products updateOneStock(Integer stock, String productId);
    public void deleteProductsBySupplierId(String supplierId);
}
