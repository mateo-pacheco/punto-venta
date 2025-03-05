package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.ClientReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.ClientReport;

@Mapper(componentModel = "spring")
public interface ClientReportMapper {
    @Mappings({
            @Mapping(target = "clientReportIdDomain", source = "clientReportId"),
            @Mapping(target = "clientIdDomain", source = "clientId"),
            @Mapping(target = "totalIncomeDomain", source = "totalIncome"),
            @Mapping(target = "totalProductsBoughtDomain", source = "totalProductsBought")
    })
    ClientReportDomain toClientReportDomain(ClientReport clientReport);
    List<ClientReportDomain> toClientReportsDomain(List<ClientReport> clientReports);
    @InheritInverseConfiguration
    ClientReport toClientReport(ClientReportDomain clientReportDomain);
}
