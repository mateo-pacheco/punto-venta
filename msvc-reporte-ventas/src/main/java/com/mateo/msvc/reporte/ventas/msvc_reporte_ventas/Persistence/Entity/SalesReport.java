package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity;

import java.time.LocalDateTime;

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
@Table(name = "sales_report")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false, unique = true)
    private Integer reportId;

    @Column(name = "generation_date", nullable = false)
    private LocalDateTime generationDate;

    @Column(name = "sales_income", nullable = false)
    private Double salesIncomeTotal;

    @Column(name = "total_products_sold", nullable = false)
    private Integer totalProductsSold;

    @Column(name = "total_sales", nullable = false)
    private Integer totalSales;
}
