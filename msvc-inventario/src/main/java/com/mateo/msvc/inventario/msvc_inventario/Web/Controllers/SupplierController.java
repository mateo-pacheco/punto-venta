package com.mateo.msvc.inventario.msvc_inventario.Web.Controllers;

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

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Suppliers;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Services.OrdersService;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Services.ProductsService;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Services.SuppliersService;

//Controllers that expose endpoints
@RestController
@RequestMapping("/suppliers")
public class SupplierController {
    @Autowired
    private SuppliersService suppliersService;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/getAllSuppliers")
    public ResponseEntity<List<Suppliers>> getAll() {
        return ResponseEntity.ok(suppliersService.getAll());
    }

    @GetMapping("/getBySupplierId/{supplierId}")
    public ResponseEntity<Suppliers> getById(@PathVariable("supplierId") String supplierId) {
        return ResponseEntity.ok(suppliersService.getBySupplierId(supplierId));
    }

    @PostMapping("/saveSupplier")
    public ResponseEntity<Suppliers> save(@RequestBody Suppliers suppliers) {
        return ResponseEntity.status(HttpStatus.CREATED).body(suppliersService.save(suppliers));
    }

    @PutMapping("/updateSupplier")
    public ResponseEntity<Suppliers> update(@RequestBody Suppliers suppliers) {
        return ResponseEntity.status(HttpStatus.CREATED).body(suppliersService.update(suppliers));
    }

    @DeleteMapping("/deleteSupplier/{supplierId}")
    public ResponseEntity<Void> delete(@PathVariable("supplierId") String supplierId) {
        productsService.deleteProductBySupplierId(supplierId);
        ordersService.deleteOrderBySupplierId(supplierId);
        suppliersService.delete(supplierId);
        return ResponseEntity.ok().build();
    }
}
