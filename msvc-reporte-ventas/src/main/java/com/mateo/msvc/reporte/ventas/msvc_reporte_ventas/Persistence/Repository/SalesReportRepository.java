package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces.SalesReportI;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.SalesReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Crud.SalesReportCrud;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.SalesReport;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Mapper.SalesReportMapper;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class SalesReportRepository implements SalesReportI {
    @Autowired
    private SalesReportCrud salesReportCrud;
    @Autowired
    private SalesReportMapper salesReportMapper;

    @Override
    public List<SalesReportDomain> getAll() {
        List<SalesReportDomain> salesReportDomain = salesReportMapper
                .toSalesRepotsDomain((List<SalesReport>) salesReportCrud.findAll());
        return salesReportDomain;
    }

    @Override
    public SalesReportDomain getById(Integer id) {
        SalesReport salesReport = salesReportCrud.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Report not found with id: " + id));
        return salesReportMapper.toSalesReportDomain(salesReport);
    }

    @Override
    public SalesReportDomain save(SalesReportDomain salesReportDomain) {
        SalesReport salesReport = salesReportMapper.toSalesReport(salesReportDomain);
        salesReport =  salesReportCrud.save(salesReport);
        return salesReportMapper.toSalesReportDomain(salesReport);
    }

    @Override
    public void delete(Integer id) {
        salesReportCrud.deleteById(id);
    }

    @Override
    public Boolean existById(Integer id) {
        return salesReportCrud.existsById(id);
    }
}
