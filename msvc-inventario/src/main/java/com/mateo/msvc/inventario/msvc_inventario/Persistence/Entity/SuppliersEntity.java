package com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity;


import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Creation of the suppliers entity class
@Entity
@Table(name = "suppliers")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SuppliersEntity {
    // Class attributes
    @Id
    @Column(name = "suppliers_id", nullable = false, unique = true)
    private String suppliersId;

    @Column(name = "name_suppliers", nullable = false)
    private String nameSuppliers;

    @Column(name = "email_suppliers", nullable = false)
    private String emailSuppliers;

    @Column(name = "number_suppliers", nullable = false)
    private String numberSuppliers;

    @Column(name = "address_suppliers", nullable = false)
    private String addressSuppliers;

    @OneToMany(mappedBy = "suppliersEntity")
    private List<ProductsEntity> productsEntities;

   @OneToMany(mappedBy = "suppliersEntity")
    private List<OrdersEntity> ordersEntity;
}
