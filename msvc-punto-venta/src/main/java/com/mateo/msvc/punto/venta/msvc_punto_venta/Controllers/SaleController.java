package com.mateo.msvc.punto.venta.msvc_punto_venta.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Sale;
import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.SaleDTO;
import com.mateo.msvc.punto.venta.msvc_punto_venta.Services.SaleService;


@RestController
@RequestMapping("/sales")
@RefreshScope
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PostMapping("/save")
    public List<Sale> saveSale(@RequestBody SaleDTO saleDTO) {
        return saleService.saveSale(saleDTO.getProducts(), saleDTO.getClientId());
    }
    
}
