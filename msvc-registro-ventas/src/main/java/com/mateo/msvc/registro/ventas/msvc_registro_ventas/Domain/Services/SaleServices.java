package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Models.Sale;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Repository.SaleDomainRepository;

//Services that will be consumed from the controllers
@Service
public class SaleServices {
    @Autowired
    private SaleDomainRepository saleDomainRepository;

    @Transactional(readOnly = true)
    public List<Sale> getAll() {
        return saleDomainRepository.getAll();
    }

    @Transactional(readOnly = true)
    public Sale getById(Integer saleId) {
        return saleDomainRepository.getById(saleId);
    }

    @Transactional(readOnly = true)
    public List<Sale> getByProductId(String productId) {
        System.out.println(saleDomainRepository.getByProductId(productId));
        return saleDomainRepository.getByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<Sale> getByClientId(String clientId) {
        return saleDomainRepository.getByClientId(clientId);
    }

    @Transactional(readOnly = true)
    public List<Sale> getByDateSaleBetween(LocalDate dateStart, LocalDate dateEnd) {
        return saleDomainRepository.getByDateSaleBetween(dateStart, dateEnd);
    }

    @Transactional
    public Sale save(Sale sale) {
        if (sale.getSaleIdDomain() == null) {
            return saleDomainRepository.save(sale);
        } else {
            throw new IllegalStateException(
                    "The sale could not be saved because it does not have an id.");
        }
    }

    @Transactional
    public Sale update(Sale sale) {
        if (sale.getSaleIdDomain() != null && saleDomainRepository.existsById(sale.getSaleIdDomain())) {
            return saleDomainRepository.save(sale);
        } else {
            throw new IllegalStateException(
                    "The sale could not be updated because it does not exist.");
        }
    }

    @Transactional
    public void delete(Integer saleId) {
        if (saleDomainRepository.existsById(saleId)) {
            saleDomainRepository.delete(saleId);
        } else {
            throw new IllegalStateException(
                    "The sale could not be deleted because it does not exist.");
        }
    }
}
