package com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.ProductsEntity;

////We inherit the methods from JPA CrudRepository
public interface ProductsCrudRepository extends CrudRepository<ProductsEntity, String> {
        List<ProductsEntity> findAllByNameProduct(String nameProduct);

        List<ProductsEntity> findAllByCategoryId(String categoryId);

        List<ProductsEntity> findAllByStockProductLessThan(Integer stockProduct);

        List<ProductsEntity> findAllBySuppliersId(String suppliersId);

        List<ProductsEntity> findAllByStockProductLessThanAndSuppliersIdIgnoreCase(Integer stockProduct,
                        String suppliersId);

        @Modifying
        @Transactional
        @Query(value = "UPDATE products SET stock_product = stock_product + :newStock WHERE suppliers_id = :suppliersId AND stock_product < :stock", nativeQuery = true)
        void updateStock(@Param("suppliersId") String suppliersId, @Param("stock") Integer MinimumStock,
                        @Param("newStock") Integer newStock);

        @Modifying
        @Transactional
        @Query(value = "UPDATE products SET stock_product = :stock WHERE product_id = :productId", nativeQuery = true)
        void updateOneStock(@Param("stock") Integer stock, @Param("productId") String productId);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM products WHERE suppliers_id = :supplierId", nativeQuery = true)
        void deleteProductBySupplier(@Param("supplierId") String supplierId);
}
