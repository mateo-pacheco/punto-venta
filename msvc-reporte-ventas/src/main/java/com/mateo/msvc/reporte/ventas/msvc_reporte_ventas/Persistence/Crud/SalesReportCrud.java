package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Crud;

import org.springframework.data.repository.CrudRepository;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.SalesReport;

public interface SalesReportCrud extends CrudRepository<SalesReport, Integer> {
}
