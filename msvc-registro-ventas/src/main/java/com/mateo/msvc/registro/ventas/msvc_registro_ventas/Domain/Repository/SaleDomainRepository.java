package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Repository;

import java.time.LocalDate;
import java.util.List;

import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Models.Sale;

//Interface containing the methods that are implemented in the persistence layer
public interface SaleDomainRepository {
    public List<Sale> getAll();
    public Sale getById(Integer saleId);
    public List<Sale> getByProductId(String productId);
    public List<Sale> getByClientId(String clientId);
    public List<Sale> getByDateSaleBetween(LocalDate dateStart, LocalDate dateEnd);
    public Sale save(Sale sale); 
    public void delete(Integer saleId);
    public boolean existsById(Integer saleId);
}
