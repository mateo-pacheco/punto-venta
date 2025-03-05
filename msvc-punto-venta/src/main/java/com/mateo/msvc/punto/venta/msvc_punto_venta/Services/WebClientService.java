package com.mateo.msvc.punto.venta.msvc_punto_venta.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Client;
import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Product;
import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Sale;


@Service
public class WebClientService {
    @Autowired
    private WebClient webClientSales;
    @Autowired
    private WebClient webClientClients;
    @Autowired
    private WebClient webClientInventario;

    public Client getClientById(String clientId) {
        return webClientClients.get().uri("/clients/getByClientId/{clientId}", clientId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Client.class)
                .block();
    }

    public Product getProductById(String productId) {
        return webClientInventario.get().uri("/products/getByProductId/{productId}",
                productId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .block();
    }

    public Sale saveSale(Sale sale) {
        return webClientSales.post().uri("/recordSales/saveSale")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(sale).retrieve().bodyToMono(Sale.class).block();
    }

    public void updateProductStock(String productId, Integer stock) {
        webClientInventario.put().uri("/products/updateStock/{productId}/{quantity}", productId, stock)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
