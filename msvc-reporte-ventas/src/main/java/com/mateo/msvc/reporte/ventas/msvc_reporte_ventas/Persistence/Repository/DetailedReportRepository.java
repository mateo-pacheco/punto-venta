package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces.DetailedReportI;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.DetailedReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Crud.DetailedReportCrud;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.DetailedReport;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Mapper.DetailedReportMapper;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class DetailedReportRepository implements DetailedReportI {
    @Autowired
    private DetailedReportCrud detailedReportCrud;
    @Autowired
    private DetailedReportMapper detailedReportMapper;

    @Override
    public List<DetailedReportDomain> getAll() {
        List<DetailedReport> detailedReport = (List<DetailedReport>) detailedReportCrud.findAll();
        return detailedReportMapper.toDetailedReportsDomain(detailedReport);
    }

    @Override
    public DetailedReportDomain getById(Integer id) {
        DetailedReport detailedReport = detailedReportCrud.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Report not found with id: " + id));
        return detailedReportMapper.toDetailedReportDomain(detailedReport);
    }

    @Override
    public List<DetailedReportDomain> getAllByProductId(String productId) {
        List<DetailedReport> detailedReports = detailedReportCrud.findAllByProductId(productId);
        return detailedReportMapper.toDetailedReportsDomain(detailedReports);
    }

    @Override
    public DetailedReportDomain save(DetailedReportDomain detailedReportDomain) {
        DetailedReport detailedReport = detailedReportMapper.toDetailedReport(detailedReportDomain);
        detailedReport = detailedReportCrud.save(detailedReport);
        return detailedReportMapper.toDetailedReportDomain(detailedReport);
    }

    @Override
    public void delete(Integer id) {
        detailedReportCrud.deleteById(id);
    }

    @Override
    public Boolean existById(Integer id) {
        return detailedReportCrud.existsById(id);
    }
}
