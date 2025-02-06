package com.mateo.msvc.inventario.msvc_inventario.Domain.Repository;

import java.util.List;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Category;

//Interface containing the methods that are implemented in the persistence layer
public interface CategoryRepositoryDomain {
    public List<Category> getAll();
    public Category getById(String categoryId);
    public List<Category> getAllByName(String nameCategory);
    public Category save(Category category);
    public void delete(String categoryId);
    public boolean exist(String categoryId);
}
