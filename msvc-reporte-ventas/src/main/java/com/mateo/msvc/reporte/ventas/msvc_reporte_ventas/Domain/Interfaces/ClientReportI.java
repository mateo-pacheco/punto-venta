package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces;

import java.util.List;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.ClientReportDomain;

public interface ClientReportI {
    public List<ClientReportDomain> getAll();
    public ClientReportDomain getById(Integer id);
    public List<ClientReportDomain> getAllByClientId(String clientId);
    public ClientReportDomain save(ClientReportDomain clientReportDomain);
    public void delete(Integer id);
    public Boolean existById(Integer id);   
}
