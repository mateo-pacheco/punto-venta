package com.mateo.msvc.inventario.msvc_inventario.Persistence.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Category;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Repository.CategoryRepositoryDomain;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Crud.CategoryCrudRepository;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.CategoryEntity;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper.CategoryMapper;

import jakarta.persistence.EntityNotFoundException;

//Repository that accesses data from the database and transforms it into DTOs
@Repository
public class CategoryRepository implements CategoryRepositoryDomain {
    @Autowired
    private CategoryCrudRepository categoryCrudRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAll() {
        List<CategoryEntity> categoryEntities = (List<CategoryEntity>) categoryCrudRepository.findAll();
        return categoryMapper.toCategories(categoryEntities);
    }

    @Override
    public Category getById(String categoryId) {
        CategoryEntity categoryEntity = categoryCrudRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("Category not found whit id: " + categoryId));
        return categoryMapper.toCategory(categoryEntity);
    }

    @Override
    public List<Category> getAllByName(String nameCategory){
        List<CategoryEntity> categoryEntities = categoryCrudRepository.findAllByNameCategory(nameCategory);
        return categoryMapper.toCategories(categoryEntities);
    }

    @Override
    public Category save(Category category){
        CategoryEntity categoryEntity = categoryMapper.toCategoryEntity(category);
        categoryCrudRepository.save(categoryEntity);
        return categoryMapper.toCategory(categoryEntity);
    }

    @Override
    public void delete(String categoryId){
        categoryCrudRepository.deleteById(categoryId);
    }

    @Override
    public boolean exist(String categoryId){
        return categoryCrudRepository.existsById(categoryId);
    }
}
