package com.mateo.msvc.punto.venta.msvc_punto_venta.Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Client;
import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Product;
import com.mateo.msvc.punto.venta.msvc_punto_venta.Models.Sale;

@Service
public class SaleService {
    @Autowired
    private WebClientService webClientService;

    public List<Sale> saveSale(Map<String, Integer> products, String clientId) {

        List<Sale> sales = new ArrayList<>();

        Client client = webClientService.getClientById(clientId.toString());
        System.out.println("Client: " + client);

        products.forEach((productId, quantity) -> {
            Integer newStock = 0;
            Double totalPrice = 0.0;

            Product product = webClientService.getProductById(productId);
            System.out.println("Product: " + product);

            if (product.getStockProductDomain() < quantity) {
                throw new IllegalStateException("The product with id " + productId + " does not have enough stock.");
            }

            totalPrice = product.getPriceProductDomain() * quantity;
            Sale sale = new Sale(null, product.getNameProductDomain(), product.getProductIdDomain(), quantity,
                    product.getPriceProductDomain(),
                    totalPrice,
                    client.getNameDomain(), client.getClientIdDomain(), client.getPhoneNumberDomain(),
                    LocalDateTime.now());

            System.out.println("Sale: " + sale.toString());
            sales.add(webClientService.saveSale(sale));
            newStock = product.getStockProductDomain() - quantity;
            webClientService.updateProductStock(productId, newStock);
        });

        return sales;
    }
}
