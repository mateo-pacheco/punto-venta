package com.mateo.msvc.punto.venta.msvc_punto_venta;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Value("${config.base-url.endpoint.msvc-registro-ventas}")
    private String urlSales;
    @Value("${config.base-url.endpoint.msvc-clientes}")
    private String urlClients;
    @Value("${config.base-url.endpoint.msvc-inventario}")
    private String urlInventario;

    @Bean
    @LoadBalanced
    WebClient.Builder webClient() {
        return WebClient.builder();
    }

    @Bean
    WebClient webClientSales(WebClient.Builder builder) {
        return builder.baseUrl(urlSales).build();
    }

    @Bean
    WebClient webClientClients(WebClient.Builder builder) {
        return builder.baseUrl(urlClients).build();
    }

    @Bean
    WebClient webClientInventario(WebClient.Builder builder) {
        return builder.baseUrl(urlInventario).build();
    }
}
