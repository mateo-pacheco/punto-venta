package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces.ClientReportI;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.ClientReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Crud.ClientReportCrud;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Entity.ClientReport;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Persistence.Mapper.ClientReportMapper;

import jakarta.persistence.EntityNotFoundException;

@Repository
public class ClientReportRepository implements ClientReportI{
    @Autowired
    private ClientReportCrud clientReportCrud;
    @Autowired
    private ClientReportMapper clientReportMapper;

    @Override
    public List<ClientReportDomain> getAll() {
        List<ClientReport> clientReport = (List<ClientReport>) clientReportCrud.findAll();
        return clientReportMapper.toClientReportsDomain(clientReport);
    }

    @Override
    public ClientReportDomain getById(Integer id) {
        ClientReport clientReport = clientReportCrud.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Report not found with id: " + id));
        return clientReportMapper.toClientReportDomain(clientReport);
    }

    @Override
    public List<ClientReportDomain> getAllByClientId(String clientId) {
        List<ClientReport> clientReports = clientReportCrud.findAllByClientId(clientId);
        return clientReportMapper.toClientReportsDomain(clientReports);
    }

    @Override
    public ClientReportDomain save(ClientReportDomain clientReportDomain) {
        ClientReport clientReport = clientReportMapper.toClientReport(clientReportDomain);
        clientReport = clientReportCrud.save(clientReport);
        return clientReportMapper.toClientReportDomain(clientReport);
    }

    @Override
    public void delete(Integer id) {
        clientReportCrud.deleteById(id);
    }

    @Override
    public Boolean existById(Integer id) {
        return clientReportCrud.existsById(id);
    }
}
