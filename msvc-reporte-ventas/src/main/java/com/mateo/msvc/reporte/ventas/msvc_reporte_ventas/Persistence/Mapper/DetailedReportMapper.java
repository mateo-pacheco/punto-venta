package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.DetailedReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.DetailedReport;

@Mapper(componentModel = "spring")
public interface DetailedReportMapper {
    @Mappings({
        @Mapping(source = "reportDetailedId", target = "reportDetailedIdDomain"),
        @Mapping(source = "date", target = "dateDomain"),
        @Mapping(source = "productId", target = "productIdDomain"),
        @Mapping(source = "quantitySold", target = "quantitySoldDomain"),
        @Mapping(source = "totalIncome", target = "totalIncomeDomain")
    })
    DetailedReportDomain toDetailedReportDomain(DetailedReport detailedReport);
    List<DetailedReportDomain> toDetailedReportsDomain(List<DetailedReport> detailedReports);
    
    @InheritInverseConfiguration
    DetailedReport toDetailedReport(DetailedReportDomain detailedReportDomain);
}
