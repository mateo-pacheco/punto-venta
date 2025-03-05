package com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Models.Sale;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Domain.Repository.SaleDomainRepository;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Crud.SaleCrudRepository;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Entity.SaleEntity;
import com.mateo.msvc.registro.ventas.msvc_registro_ventas.Persistence.Mapper.SaleMapper;

import jakarta.persistence.EntityNotFoundException;

//Repository that accesses data from the database and transforms it into DTOs
@Repository
public class SaleRepository implements SaleDomainRepository {
    @Autowired
    private SaleCrudRepository saleCrudRepository;
    @Autowired
    private SaleMapper saleMapper;

    @Override
    public List<Sale> getAll() {
        List<SaleEntity> saleEntities = (List<SaleEntity>) saleCrudRepository.findAll();
        return saleMapper.toSales(saleEntities);
    }

    @Override
    public Sale getById(Integer saleId) {
        SaleEntity saleEntity = saleCrudRepository.findById(saleId).orElseThrow(
                () -> new EntityNotFoundException("Sale not found with id: " + saleId));
        return saleMapper.toSale(saleEntity);
    }

    @Override
    public List<Sale> getByProductId(String productId) {
        List<SaleEntity> saleEntities = saleCrudRepository.findAllByProductId(productId);
        System.out.println(saleEntities);
        return saleMapper.toSales(saleEntities);
    }

    @Override
    public List<Sale> getByClientId(String clientId) {
        List<SaleEntity> saleEntities = saleCrudRepository.findAllByClientId(clientId);
        return saleMapper.toSales(saleEntities);
    }

    @Override
    public List<Sale> getByDateSaleBetween(LocalDate dateStart, LocalDate dateEnd) {
        List<SaleEntity> saleEntities = saleCrudRepository.findAllByDateSaleBetween(dateStart, dateEnd);
        return saleMapper.toSales(saleEntities);
    }

    @Override
    public Sale save(Sale sale) {
        SaleEntity saleEntity = saleMapper.toSaleEntity(sale);
        saleEntity = saleCrudRepository.save(saleEntity);
        return saleMapper.toSale(saleEntity);
    }

    @Override
    public void delete(Integer saleId) {
        saleCrudRepository.deleteById(saleId);
    }

    @Override
    public boolean existsById(Integer saleId) {
        return saleCrudRepository.existsById(saleId);
    }
}
