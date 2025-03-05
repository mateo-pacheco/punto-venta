package com.mateo.msvc.punto.venta.msvc_punto_venta.Models;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaleDTO {
    private Map<String, Integer> products;
    private String clientId;
}
