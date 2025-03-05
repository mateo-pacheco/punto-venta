package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.Sale;

@Service
public class ReportWebClientService {
    @Autowired
    private WebClient.Builder webClient;

    public List<Sale> getSales() {
        return webClient.build().get().uri("/recordSales/getAllSales")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Sale.class)
                .collectList()
                .block();
    }

    public List<Sale> getAllByClientId(String clientId) {
        return webClient.build().get().uri("/recordSales/getSalesByClientId/{clientId}", clientId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Sale.class)
                .collectList()
                .block();
    }

    public List<Sale> getAllByProductId(String productId) {
        return webClient.build().get().uri("/recordSales/getSalesByProductId/{productId}", productId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(Sale.class)
                .collectList()
                .block();
    }
}
