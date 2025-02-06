package com.mateo.msvc.inventario.msvc_inventario.Domain.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Category;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.CategoryRepositoryDomain;

//Services that will be consumed from the controllers
@Service
public class CategoryService {
    @Autowired
    private CategoryRepositoryDomain categoryRepositoryDomain;

    @Transactional(readOnly = true)
    public List<Category> getAll() {
        return categoryRepositoryDomain.getAll();
    }

    @Transactional(readOnly = true)
    public Category getById(String categoryId) {
        return categoryRepositoryDomain.getById(categoryId);
    }

    @Transactional(readOnly = true)
    public List<Category> getAllByName(String nameCategory) {
        return categoryRepositoryDomain.getAllByName(nameCategory);
    }

    @Transactional
    public Category save(Category category) {
        if (category.getCategoryIdDomain() != null && !categoryRepositoryDomain.exist(category.getCategoryIdDomain())) {
            return categoryRepositoryDomain.save(category);
        } else {
            throw new IllegalStateException("The category could not be saved because it already exists.");
        }
    }

    @Transactional
    public Category update(Category category) {
        if (category.getCategoryIdDomain() != null && categoryRepositoryDomain.exist(category.getCategoryIdDomain())) {
            return categoryRepositoryDomain.save(category);
        } else {
            throw new IllegalStateException(
                    "The category could not be updated because it does not have an ID or does not exite.");
        }
    }

    @Transactional
    public void delete(String categoryId) {
        if (categoryRepositoryDomain.exist(categoryId)) {
            categoryRepositoryDomain.delete(categoryId);
        } else {
            throw new IllegalStateException("The category could not be eliminated since it does not exist.");
        }
    }
}
