package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Interfaces.SalesReportI;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.Sale;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.SalesReportDomain;

@Service
public class SalesReportService {
    @Autowired
    private SalesReportI salesReportI;
    @Autowired
    private ReportWebClientService WebClientService;

    @Transactional(readOnly = true)
    public List<SalesReportDomain> getAll() {
        return salesReportI.getAll();
    }

    @Transactional(readOnly = true)
    public SalesReportDomain getById(Integer id) {
        return salesReportI.getById(id);
    }

    @Transactional
    public SalesReportDomain save() {
        Double salesIncome = 0.0;
        Integer totalProducts = 0;
        Integer totalSales = 0;
        List<Sale> sales = WebClientService.getSales();
        for (Sale sale : sales) {
            salesIncome += sale.getTotalPriceDomain();
            totalProducts += sale.getQuantityProductsDomain();
        }
        totalSales = sales.size();
        SalesReportDomain salesReportDomain = new SalesReportDomain(LocalDateTime.now(), salesIncome, totalProducts,
                totalSales);
        salesReportI.save(salesReportDomain);
        return salesReportDomain;
    }

    @Transactional
    public void delete(Integer id) {
        if (salesReportI.existById(id)) {
            salesReportI.delete(id);
        } else {
            throw new IllegalArgumentException("The sales report does not exist");
        }
    }
}
