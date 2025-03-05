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

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Products;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Services.ProductsService;

//Controllers that expose endpoints
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok(productsService.getAll());
    }

    @GetMapping("/getByProductId/{productId}")
    public ResponseEntity<Products> getById(@PathVariable("productId") String productId) {
        return ResponseEntity.ok(productsService.getById(productId));
    }

    @GetMapping("/getAllByProductName/{productName}")
    public ResponseEntity<List<Products>> getAllByName(@PathVariable("productName") String productName) {
        return ResponseEntity.ok(productsService.getAllByName(productName));
    }

    @GetMapping("/getAllByCategoryId/{categoryId}")
    public ResponseEntity<List<Products>> getAllByCategoryId(@PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok(productsService.getAllByCategoryId(categoryId));
    }

    @GetMapping("/getAllByLessStock/{sotck}")
    public ResponseEntity<List<Products>> getAllByStock(@PathVariable("sotck") Integer stock) {
        return ResponseEntity.ok(productsService.getAllByStock(stock));
    }

    @GetMapping("/getAllBySupplierId/{supplierId}")
    public ResponseEntity<List<Products>> getAllBySupplierId(@PathVariable("supplierId") String supllierId) {
        return ResponseEntity.ok(productsService.getAllBySuppliersId(supllierId));
    }

    @PostMapping("/saveProduct")
    public ResponseEntity<Products> save(@RequestBody Products products) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.save(products));
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<Products> update(@RequestBody Products products) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productsService.update(products));
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<Void> delete(@PathVariable("productId") String productId) {
        productsService.delete(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/updateStock/{productId}/{quantity}")
    public ResponseEntity<Void> updateStock(@PathVariable("productId") String productId,
            @PathVariable("quantity") Integer quantity) {
        productsService.updateStock(quantity, productId);
        return ResponseEntity.ok().build();
    }
}
