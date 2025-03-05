package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetailedReportDomain {
    private Integer reportDetailedIdDomain;
    private LocalDateTime dateDomain;
    private String productIdDomain;
    private Integer quantitySoldDomain;
    private Double totalIncomeDomain;

    public DetailedReportDomain(LocalDateTime dateDomain, String productIdDomain, Integer quantitySoldDomain,
            Double totalIncomeDomain) {
        this.dateDomain = dateDomain;
        this.productIdDomain = productIdDomain;
        this.quantitySoldDomain = quantitySoldDomain;
        this.totalIncomeDomain = totalIncomeDomain;
    }

}
