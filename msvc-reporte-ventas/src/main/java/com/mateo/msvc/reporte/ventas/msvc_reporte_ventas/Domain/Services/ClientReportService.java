package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces.ClientReportI;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.ClientReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.Sale;

@Service
public class ClientReportService {
    @Autowired
    private ClientReportI clientReportI;
    @Autowired
    private ReportWebClientService reportWebClientService;

    @Transactional(readOnly = true)
    public List<ClientReportDomain> getAll() {
        return clientReportI.getAll();
    }

    @Transactional(readOnly = true)
    public ClientReportDomain getById(Integer id) {
        return clientReportI.getById(id);
    }

    @Transactional(readOnly = true)
    public List<ClientReportDomain> getAllByClientId(String clientId) {
        return clientReportI.getAllByClientId(clientId);
    }

    @Transactional
    public ClientReportDomain save(String id) {
        if (id == null) {
            throw new IllegalArgumentException("The id cannot be null");
        }
        Double totalIncome = 0.0;
        Integer totalProductsBought = 0;
        List<Sale> sales = reportWebClientService.getAllByClientId(id);
        for (Sale sale : sales) {
            totalIncome += sale.getTotalPriceDomain();
            totalProductsBought += sale.getQuantityProductsDomain();
        }

        ClientReportDomain clientReportDomain = new ClientReportDomain(id, totalIncome, totalProductsBought);
        clientReportI.save(clientReportDomain);
        return clientReportDomain;
    }

    @Transactional
    public void delete(Integer id) {
        if (clientReportI.existById(id)) {
            clientReportI.delete(id);
        } else {
            throw new IllegalArgumentException("The client report does not exist");
        }
    }
}
