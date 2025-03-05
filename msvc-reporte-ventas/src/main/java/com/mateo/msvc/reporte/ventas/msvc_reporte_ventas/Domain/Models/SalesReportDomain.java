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
public class SalesReportDomain {
    private Integer reportIdDomain;
    private LocalDateTime generationDateDomain;
    private Double salesIncomeTotalDomain;
    private Integer totalProductsSoldDomain;
    private Integer totalSalesDomain;

    public SalesReportDomain(LocalDateTime generationDateDomain, Double salesIncomeTotalDomain,
            Integer totalProductsSoldDomain, Integer totalSalesDomain) {
        this.generationDateDomain = generationDateDomain;
        this.salesIncomeTotalDomain = salesIncomeTotalDomain;
        this.totalProductsSoldDomain = totalProductsSoldDomain;
        this.totalSalesDomain = totalSalesDomain;
    }

}
