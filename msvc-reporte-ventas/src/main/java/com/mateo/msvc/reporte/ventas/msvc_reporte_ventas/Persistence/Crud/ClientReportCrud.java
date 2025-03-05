package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Crud;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.ClientReport;

public interface ClientReportCrud extends CrudRepository<ClientReport, Integer> {
    List<ClientReport> findAllByClientId(String ClientId);
}
