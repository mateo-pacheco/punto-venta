package com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Creation of the category entity class
@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity {
    // Class attributes
    @Id
    @Column(name = "category_id", nullable = false, unique = true)
    private String categoryId;

    @Column(name = "name_category", nullable = false)
    private String nameCategory;

    @Column(name = "creation_date", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "categoryEntity", fetch = FetchType.EAGER)
    private List<ProductsEntity> productsEntities;
}
