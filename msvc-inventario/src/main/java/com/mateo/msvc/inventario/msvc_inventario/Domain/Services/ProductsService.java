package com.mateo.msvc.inventario.msvc_inventario.Domain.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Products;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.ProductsRepositoryDomain;

//Services that will be consumed from the controllers
@Service
public class ProductsService {
    @Autowired
    private ProductsRepositoryDomain productsRepositoryDomain;

    @Transactional(readOnly = true)
    public List<Products> getAll(){
        return productsRepositoryDomain.getAll();
    }

    @Transactional(readOnly = true)
    public Products getById(String productsId){
        return productsRepositoryDomain.getByProductId(productsId);
    }

    @Transactional(readOnly = true)
    public List<Products> getAllByName(String nameProduct){
        return productsRepositoryDomain.getAllByNameProduct(nameProduct);
    }

    @Transactional(readOnly = true)
    public List<Products> getAllByCategoryId(String categoryId){
        return productsRepositoryDomain.getAllByCategoryId(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Products> getAllByStock(Integer stockProduct){
        return productsRepositoryDomain.getAllByStockProduct(stockProduct);
    }

    @Transactional(readOnly = true)
    public List<Products> getAllBySuppliersId(String suppliersId){
        return productsRepositoryDomain.getAllBySuppliersId(suppliersId);
    }

    @Transactional
    public Products save(Products products){
        if (products.getProductIdDomain() != null && !productsRepositoryDomain.exist(products.getProductIdDomain())) {
            return productsRepositoryDomain.save(products);
        }else{
            throw new IllegalStateException("The product could not be saved because it already exists.");
        }
    }

    @Transactional
    public Products update(Products products){
        if (products.getProductIdDomain() != null && productsRepositoryDomain.exist(products.getProductIdDomain())) {
            return productsRepositoryDomain.save(products);
        }else{
            throw new IllegalStateException("The product could not be updated because it does not have an ID or does not exite.");
        }
    }

    @Transactional
    public void delete(String productsId){
        if (productsRepositoryDomain.exist(productsId)) {
            productsRepositoryDomain.delete(productsId);
        }else{
            throw new IllegalStateException("The product could not be eliminated since it does not exist.");
        }
    }

    @Transactional
    public void updateStock(Integer stock, String productId){
        productsRepositoryDomain.updateOneStock(stock, productId);
    }

    @Transactional
    public void deleteProductBySupplierId(String supplierId){
        productsRepositoryDomain.deleteProductsBySupplierId(supplierId);
    }
}
