package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces;

import java.util.List;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.SalesReportDomain;

public interface SalesReportI {
    public List<SalesReportDomain> getAll();
    public SalesReportDomain getById(Integer id);
    public SalesReportDomain save(SalesReportDomain salesReportDomain);
    public void delete(Integer id);
    public Boolean existById(Integer id);
}
