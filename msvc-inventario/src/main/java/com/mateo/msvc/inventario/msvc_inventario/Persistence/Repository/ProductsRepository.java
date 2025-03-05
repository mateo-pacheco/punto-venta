package com.mateo.msvc.inventario.msvc_inventario.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Products;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.ProductsRepositoryDomain;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud.ProductsCrudRepository;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.ProductsEntity;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper.ProductsMapper;

import jakarta.persistence.EntityNotFoundException;

//Repository that accesses data from the database and transforms it into DTOs
@Repository
public class ProductsRepository implements ProductsRepositoryDomain{
    @Autowired
    private ProductsCrudRepository productsCrudRepository;
    @Autowired
    private ProductsMapper productsMapper;

    @Override
    public List<Products> getAll(){
        List<ProductsEntity> productsEntities = (List<ProductsEntity>) productsCrudRepository.findAll();
        return productsMapper.toProducts(productsEntities);
    }

    @Override
    public Products getByProductId(String productsId){
        ProductsEntity productsEntity = productsCrudRepository.findById(productsId).orElseThrow(
            () -> new EntityNotFoundException("Product not found with id: " + productsId));
        return productsMapper.toProduct(productsEntity);
    }

    @Override
    public List<Products> getAllByNameProduct(String nameProduct){
        List<ProductsEntity> productsEntities = productsCrudRepository.findAllByNameProduct(nameProduct);
        return productsMapper.toProducts(productsEntities);
    }

    @Override
    public List<Products> getAllByCategoryId(String categoryId){
        List<ProductsEntity> productsEntities = productsCrudRepository.findAllByCategoryId(categoryId);
        return productsMapper.toProducts(productsEntities);
    }

    @Override
    public List<Products> getAllByStockProduct(Integer stockProduct){
        List<ProductsEntity> productsEntities = productsCrudRepository.findAllByStockProductLessThan(stockProduct);
        return productsMapper.toProducts(productsEntities);
    }

    @Override
    public List<Products> getAllBySuppliersId(String suppliersId){
        List<ProductsEntity> productsEntities = productsCrudRepository.findAllBySuppliersId(suppliersId);
        return productsMapper.toProducts(productsEntities);
    }

    @Override
    public Products save(Products products){
        ProductsEntity productsEntity = productsMapper.toProductsEntity(products);
        productsCrudRepository.save(productsEntity);
        return productsMapper.toProduct(productsEntity);
    }

    @Override
    public void delete(String productsId){
        productsCrudRepository.deleteById(productsId);
    }

    @Override
    public boolean exist(String productsId){
        return productsCrudRepository.existsById(productsId);
    }

    @Override
    public List<Products> getAllByStockAndSuppliers(Integer stockProduct, String suppliersId){
        List<ProductsEntity> productsEntities = productsCrudRepository.findAllByStockProductLessThanAndSuppliersIdIgnoreCase(stockProduct, suppliersId);
        return productsMapper.toProducts(productsEntities);
    }

    @Override
    public void updateStock(String suppliersId, Integer MinimumStock, Integer newStock){
        productsCrudRepository.updateStock(suppliersId, MinimumStock, newStock);
    }

    @Override
    public void updateOneStock(Integer stock, String productId){
        productsCrudRepository.updateOneStock(stock, productId);
    }

    @Override
    public void deleteProductsBySupplierId(String supplierId){
        productsCrudRepository.deleteProductBySupplier(supplierId);
    }
}
