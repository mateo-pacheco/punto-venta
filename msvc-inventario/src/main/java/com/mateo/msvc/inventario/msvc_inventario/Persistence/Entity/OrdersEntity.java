package com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Creation of the orders entity class
@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdersEntity {
    // Class attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false, unique = true)
    private Integer orderId;

    @Column(name = "suppliers_id")
    private String suppliersId;

    @Column(name = "date_order", nullable = false, columnDefinition = "DATETIME")
    private LocalDateTime dateOrder;

    @Column(name = "quantity_products", nullable = false)
    private Integer quantityProducts;

    @Column(name = "products_order", nullable = false)
    private String productsOrder;

    @Column(name = "state_order", nullable = false, columnDefinition = "TINYINT")
    private Boolean stateOrder;


    @ManyToOne
    @JoinColumn(name = "suppliers_id", referencedColumnName = "suppliers_id", insertable = false, updatable = false)
    @JsonIgnore
    private SuppliersEntity suppliersEntity;
}
