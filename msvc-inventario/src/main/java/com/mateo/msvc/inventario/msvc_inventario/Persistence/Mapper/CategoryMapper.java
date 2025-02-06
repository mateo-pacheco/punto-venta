package com.mateo.msvc.inventario.msvc_inventario.Persistence.Mapper;

import java.util.List;


import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Category;
import com.mateo.msvc.inventario.msvc_inventario.Persistence.Entity.CategoryEntity;

//We map the entity classes to the DTOs
@Mapper(componentModel = "spring", uses = {ProductsMapper.class})
public interface CategoryMapper {
    @Mappings({
        @Mapping(source = "categoryId", target = "categoryIdDomain"),
        @Mapping(source = "nameCategory", target = "nameCategoryDomain"),
        @Mapping(source = "creationDate", target = "creationDateDomain"),
        @Mapping(source = "productsEntities", target = "productsEntitiesDomain")
    })
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategories(List<CategoryEntity> categoryEntities);

    @InheritInverseConfiguration()
    CategoryEntity toCategoryEntity(Category category);
}
