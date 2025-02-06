package com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Creation of the products entity class
@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
    // Class attributes
    @Id
    @Column(name = "product_id", nullable = false, unique = true)
    private String productId;

    @Column(name = "suppliers_id", nullable = false)
    private String suppliersId;

    @Column(name = "category_id", nullable = false)
    private String categoryId;

    @Column(name = "name_product", nullable = false)
    private String nameProduct;

    @Column(name = "description_product", nullable = false)
    private String descriptionProduct;

    @Column(name = "price_product", nullable = false, columnDefinition = "Decimal(5,2)")
    private Double priceProduct;

    @Column(name = "stock_product", nullable = false)
    private Integer stockProduct;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    @JsonIgnore
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "suppliers_id", referencedColumnName = "suppliers_id", insertable = false, updatable = false)
    @JsonIgnore
    private SuppliersEntity suppliersEntity;
}
