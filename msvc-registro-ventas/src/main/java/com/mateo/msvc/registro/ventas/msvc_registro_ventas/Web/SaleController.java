package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Models.Sale;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Services.SaleServices;

//Controllers that expose endpoints
@RestController
@RequestMapping("/recordSales")
public class SaleController {
    @Autowired
    private SaleServices saleServices;

    @GetMapping("/getAllSales")
    public ResponseEntity<List<Sale>> getAll(){
        return ResponseEntity.ok(saleServices.getAll());
    }

    @GetMapping("/getSaleById/{saleId}")
    public ResponseEntity<Sale> getById(@PathVariable("saleId") Integer saleId){
        return ResponseEntity.ok(saleServices.getById(saleId));
    }

    @GetMapping("/getSalesByProductId/{productId}")
    public ResponseEntity<List<Sale>> getByProductId(@PathVariable("productId") String productId){
        return ResponseEntity.ok(saleServices.getByProductId(productId));
    }

    @GetMapping("/getSalesByClientId/{clientId}")
    public ResponseEntity<List<Sale>> getByClientId(@PathVariable("clientId") String clientId){
        return ResponseEntity.ok(saleServices.getByClientId(clientId));
    }

    @GetMapping("/getSalesByDateSaleBetween/{dateStart}/{dateEnd}")
    public ResponseEntity<List<Sale>> getByDateSaleBetween(@PathVariable("dateStart") LocalDate dateStart,@PathVariable("dateEnd") LocalDate dateEnd){
        return ResponseEntity.ok(saleServices.getByDateSaleBetween(dateStart, dateEnd));
    }

    @PostMapping("/saveSale")
    public ResponseEntity<Sale> save(@RequestBody Sale sale){
        return ResponseEntity.status(HttpStatus.CREATED).body(saleServices.save(sale));
    }

    @PutMapping("/updateSale")
    public ResponseEntity<Sale> update(@RequestBody Sale sale){
        return ResponseEntity.status(HttpStatus.CREATED).body(saleServices.update(sale));
    }

    @DeleteMapping("/deleteSale/{saleId}")
    public ResponseEntity<Void> delete(@PathVariable("saleId") Integer saleId){
        saleServices.delete(saleId);
        return ResponseEntity.ok().build();
    }
}
