package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Web.Controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.DetailedReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services.DetailedReportService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RestController
@RequestMapping("/detailedReport")
@RefreshScope
public class DetailedReportController {
    @Autowired
    private DetailedReportService detailedReportService;


    @GetMapping("/getAll")
    public ResponseEntity<List<DetailedReportDomain>> getAll() {
        return ResponseEntity.ok(detailedReportService.getAll());
    }

    @GetMapping("/getAllById/{id}")
    public ResponseEntity<DetailedReportDomain> getAllById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(detailedReportService.getById(id));
    }

    @GetMapping("/getAllByProductId/{productId}")
    public ResponseEntity<List<DetailedReportDomain>> getAllByProductId(@PathVariable("productId") String productId) {
        return ResponseEntity.ok(detailedReportService.getAllByProductId(productId));
    }

    @CircuitBreaker(name = "reports", fallbackMethod = "getFallback2")
    @TimeLimiter(name = "reports")
    @PostMapping("/save/{productId}")
    public CompletableFuture<?> save(@PathVariable("productId") String productId){
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.status(HttpStatus.CREATED).body(detailedReportService.save(productId));
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        detailedReportService.delete(id);
        return ResponseEntity.ok().build();
    }

        public CompletableFuture<?> getFallback1(Throwable t) {
        return CompletableFuture.supplyAsync(() -> {
            List<DetailedReportDomain> list = new ArrayList<>();
            list.add(new DetailedReportDomain(1, LocalDateTime.now(), "000000", 1000, 1000.0));
            list.add(new DetailedReportDomain(1, LocalDateTime.now(), "000000", 1000, 1000.0));
            return ResponseEntity.ok(list);
        });
    }

    public CompletableFuture<?> getFallback2(Throwable t) {
        return CompletableFuture.supplyAsync(() -> {
            DetailedReportDomain detailedReportDomain = new DetailedReportDomain(1, LocalDateTime.now(), "000000", 1000, 1000.0);
            return ResponseEntity.ok(detailedReportDomain);
        });
    }
}
