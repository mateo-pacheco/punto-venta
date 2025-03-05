package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces;

import java.util.List;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.DetailedReportDomain;

public interface DetailedReportI {
    public List<DetailedReportDomain> getAll();
    public DetailedReportDomain getById(Integer id);
    public List<DetailedReportDomain> getAllByProductId(String productId);
    public DetailedReportDomain save(DetailedReportDomain detailedReportDomain);
    public void delete(Integer id);
    public Boolean existById(Integer id);
}
