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

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.SalesReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services.SalesReportService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;


@RefreshScope
@RestController
@RequestMapping("/salesReport")
public class SalesReportController {
    @Autowired
    private SalesReportService salesService;

    @CircuitBreaker(name = "reports", fallbackMethod = "getFallback1")
    @TimeLimiter(name = "reports")
    @GetMapping("/getAllRepots")
    public ResponseEntity<List<SalesReportDomain>> getAll() {
        return ResponseEntity.ok(salesService.getAll());
    }

    @CircuitBreaker(name = "reports", fallbackMethod = "getFallback2")
    @TimeLimiter(name = "reports")
    @GetMapping("/getByReportId/{id}")
    public CompletableFuture<?> getById(@PathVariable("id") Integer id){
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.ok(salesService.getById(id));
        });
    }

    @CircuitBreaker(name = "reports", fallbackMethod = "getFallback2")
    @TimeLimiter(name = "reports")
    @PostMapping("/saveReport")
    public ResponseEntity<SalesReportDomain> save() {
        return ResponseEntity.status(HttpStatus.CREATED).body(salesService.save());
    }

    @DeleteMapping("/deleteReport/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        salesService.delete(id);
        return ResponseEntity.ok().build();
    }

    public CompletableFuture<?> getFallback1(Throwable t) {
        return CompletableFuture.supplyAsync(() -> {
            List<SalesReportDomain> list = new ArrayList<>();
            list.add(new SalesReportDomain(1, LocalDateTime.now(), 1000.0, 1000, 1000));
            list.add(new SalesReportDomain(1, LocalDateTime.now(), 5000.0, 5000, 5000));
            return ResponseEntity.ok(list);
        });
    }

    public CompletableFuture<?> getFallback2(Throwable t) {
        return CompletableFuture.supplyAsync(() -> {
            SalesReportDomain salesReportDomain = new SalesReportDomain(1, LocalDateTime.now(), 3000.0, 3000, 3000);
            return ResponseEntity.ok(salesReportDomain);
        });
    }
}
