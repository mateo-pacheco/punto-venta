package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.SalesReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.SalesReport;

@Mapper(componentModel = "spring")
public interface SalesReportMapper {
    @Mappings({
        @Mapping(source = "reportId", target = "reportIdDomain"),
        @Mapping(source = "generationDate", target = "generationDateDomain"),
        @Mapping(source = "salesIncomeTotal", target = "salesIncomeTotalDomain"),
        @Mapping(source = "totalProductsSold", target = "totalProductsSoldDomain"),
        @Mapping(source = "totalSales", target = "totalSalesDomain")
    })
    SalesReportDomain toSalesReportDomain(SalesReport salesReport);
    List<SalesReportDomain> toSalesRepotsDomain(List<SalesReport> salesReports);

    @InheritInverseConfiguration
    SalesReport toSalesReport(SalesReportDomain salesReportDomain);
}
