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

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Orders;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Services.OrdersService;

//Controllers that expose endpoints
@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Orders>> getAll() {
        return ResponseEntity.ok(ordersService.getAll());
    }

    @GetMapping("/getByOrderId/{orderId}")
    public ResponseEntity<Orders> getByOrderId(@PathVariable("orderId") Integer orderId) {
        return ResponseEntity.ok(ordersService.getById(orderId));
    }

    @GetMapping("/getAllBySuppliersId/{suppliersId}")
    public ResponseEntity<List<Orders>> getAllBySuppliersId(@PathVariable("suppliersId") String suppliersId) {
        return ResponseEntity.ok(ordersService.getAllBySuppliersId(suppliersId));
    }

    @PostMapping("/saveOrder/{stock}")
    public ResponseEntity<Orders> save(@RequestBody Orders orders, @PathVariable("stock") Integer stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.save(orders, stock));
    }

    @PutMapping("/updateOrder/{stock}")
    public ResponseEntity<Orders> update(@RequestBody Orders orders, @PathVariable("stock") Integer stock) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.update(orders, stock));
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<Void> delete(@PathVariable("orderId") Integer orderId) {
        ordersService.delete(orderId);
        return ResponseEntity.ok().build();
    }
}
