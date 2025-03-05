package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientReportDomain {
    private Integer clientReportIdDomain;
    private String clientIdDomain;
    private Double totalIncomeDomain;
    private Integer totalProductsBoughtDomain;

    public ClientReportDomain(String clientIdDomain, Double totalIncomeDomain, Integer totalProductsBoughtDomain) {
        this.clientIdDomain = clientIdDomain;
        this.totalIncomeDomain = totalIncomeDomain;
        this.totalProductsBoughtDomain = totalProductsBoughtDomain;
    }

}
