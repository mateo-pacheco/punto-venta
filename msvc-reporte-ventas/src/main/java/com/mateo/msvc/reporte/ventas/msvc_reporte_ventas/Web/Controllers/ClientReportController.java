package com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Web.Controllers;

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

import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Models.ClientReportDomain;
import com.mateo.msvc.reporte.ventas.msvc_reporte_ventas.Domain.Services.ClientReportService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;

@RefreshScope
@RestController
@RequestMapping("/clientReport")
public class ClientReportController {
    @Autowired
    private ClientReportService clientReportService;

    @GetMapping("/getAll")
    public ResponseEntity<List<ClientReportDomain>> getAll() {
        return ResponseEntity.ok(clientReportService.getAll());
    }

    @CircuitBreaker(name = "reports", fallbackMethod = "getFallback2")
    @TimeLimiter(name = "reports")
    @GetMapping("/getById/{id}")
    public CompletableFuture<?> getById(@PathVariable("id") Integer id) {
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.ok(clientReportService.getById(id));
        });
    }

    @GetMapping("/getAllByClientId/{clientId}")
    public ResponseEntity<List<ClientReportDomain>> getAllByClientId(@PathVariable("clientId") String clientId) {
        return ResponseEntity.ok(clientReportService.getAllByClientId(clientId));
    }

    @CircuitBreaker(name = "reports", fallbackMethod = "getFallback2")
    @TimeLimiter(name = "reports")
    @PostMapping("/save/{clientId}")
    public CompletableFuture<?> save(@PathVariable("clientId") String clientId) {
        return CompletableFuture.supplyAsync(() -> {
            return ResponseEntity.status(HttpStatus.CREATED).body(clientReportService.save(clientId));
        });
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        clientReportService.delete(id);
        return ResponseEntity.ok().build();
    }

    public CompletableFuture<?> getFallback1(Throwable t) {
        return CompletableFuture.supplyAsync(() -> {
            List<ClientReportDomain> list = new ArrayList<>();
            list.add(new ClientReportDomain(1, "99999922", 1000.0, 1000));
            list.add(new ClientReportDomain(1, "99999922", 5000.0, 5000));
            return ResponseEntity.ok(list);
        });
    }

    public CompletableFuture<?> getFallback2(Throwable t) {
        return CompletableFuture.supplyAsync(() -> {
            ClientReportDomain clientReportDomain = new ClientReportDomain(1, "99999922", 1000.0, 1000);
            return ResponseEntity.ok(clientReportDomain);
        });
    }
}
