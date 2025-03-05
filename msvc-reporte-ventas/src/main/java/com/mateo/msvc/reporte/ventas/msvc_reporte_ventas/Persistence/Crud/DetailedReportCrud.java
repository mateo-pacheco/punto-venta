package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.DetailedReport;

public interface DetailedReportCrud extends CrudRepository<DetailedReport, Integer> {
    List<DetailedReport> findAllByProductId(String productId);
}
