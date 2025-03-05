package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity;

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
@Table(name = "client_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_report_id", nullable = false, unique = true)
    private Integer clientReportId;

    @Column(name = "client_id", nullable = false)
    private String clientId;

    @Column(name = "total_income", nullable = false)
    private Double totalIncome;

    @Column(name = "total_products_bought", nullable = false)
    private Integer totalProductsBought;
}
