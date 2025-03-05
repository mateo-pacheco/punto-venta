package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces.DetailedReportI;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.DetailedReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.Sale;

@Service
public class DetailedReportService {
    @Autowired
    private DetailedReportI detailedReportI;
    @Autowired
    private ReportWebClientService clientService;

    public List<DetailedReportDomain> getAll() {
        return detailedReportI.getAll();
    }

    public DetailedReportDomain getById(Integer id) {
        return detailedReportI.getById(id);
    }

    public List<DetailedReportDomain> getAllByProductId(String productId) {
        return detailedReportI.getAllByProductId(productId);
    }

    public DetailedReportDomain save(String productId) {
        Integer quantity = 0;
        Double totalIncome = 0.0;
        List<Sale> products = clientService.getAllByProductId(productId);
        for (Sale sale : products) {
            quantity += sale.getQuantityProductsDomain();
            totalIncome += sale.getTotalPriceDomain();
        }
        DetailedReportDomain detailedReport = new DetailedReportDomain(LocalDateTime.now(), productId, quantity,
                totalIncome);
        return detailedReportI.save(detailedReport);
    }
    
    public void delete(Integer id) {
        if (detailedReportI.existById(id)) {
            detailedReportI.delete(id);
        }else {
            throw new IllegalArgumentException("The detailed report does not exist");
        }
    }
}
