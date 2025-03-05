package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//Creation of the orders entity class
public class SaleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id", nullable = false, unique = true)
    private Integer saleId;

    @Column(name = "name_products", nullable = false)
    private String nameProducts;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "quantity_products", nullable = false)
    private Integer quantityProducts;

    @Column(name = "unit_price", nullable = false, columnDefinition = "Decimal(10,2)")
    private Double unitPrice;

    @Column(name = "total_price", nullable = false, columnDefinition = "Decimal(10,2)")
    private Double totalPrice;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "contact_client", nullable = false)
    private String contactClient;

    @Column(name = "date_sale", nullable = false, columnDefinition = "DATETIME")
    private LocalDate dateSale;
}
