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
@Table(name = "detailed_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_detailed_id", nullable = false, unique = true)
    private Integer reportDetailedId;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    
    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "quantity_sold", nullable = false)
    private Integer quantitySold;

    @Column(name = "total_income", nullable = false)
    private Double totalIncome;
}
