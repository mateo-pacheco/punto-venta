package com.mateo.msvc.inventario.msvc_inventario.Web.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mateo.msvc.inventario.msvc_inventario.Domain.Models.Category;
import com.mateo.msvc.inventario.msvc_inventario.Domain.Services.CategoryService;

//Controllers that expose endpoints
@RestController
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/getAllCategorys")
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/getByCategoryId/{categoryId}")
    public ResponseEntity<Category> getById(@PathVariable("categoryId") String categoryId) {
        return ResponseEntity.ok(categoryService.getById(categoryId));
    }

    @GetMapping("/getByCategoryName/{categoryName}")
    public ResponseEntity<List<Category>> getByName(@PathVariable("categoryName") String categoryName) {
        return ResponseEntity.ok(categoryService.getAllByName(categoryName));
    }

    @PostMapping("/saveCategory")
    public ResponseEntity<Category> save(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(category));
    }

    @PutMapping("/updateCategory")
    public ResponseEntity<Category> update(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.update(category));
    }

    @DeleteMapping("/deleteCategory/{categoryId}")
    public ResponseEntity<Void> delete(@PathVariable("categoryId") String categoryId) {
        categoryService.delete(categoryId);
        return ResponseEntity.ok().build();
    }
}
