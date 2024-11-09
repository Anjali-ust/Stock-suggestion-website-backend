package com.ust.stock_category.controller;

import com.ust.stock_category.service.CategoryService;
import com.ust.stock_category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/save")
    public ResponseEntity<Category> saveCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.saveCategory(category));
    }

    @GetMapping("/get-all-categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok().body(categoryService.getAllCategories());
    }

    @GetMapping("/get-category-by-id/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(name="id") String id){
        Optional<Category> categoryOptional = categoryService.getCategoryById(id);
        if(categoryOptional.isPresent()){
            return ResponseEntity.ok().body(categoryOptional.get());
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete-category-by-id/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable(name = "id") String id){
              return ResponseEntity.ok().body(categoryService.deleteCategoryById(id));
    }
    @PutMapping("/update-category/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable(name="id") String id,@RequestBody Category category){
        Category updatedCategory = categoryService.updateCategory(id,category);
        if(updatedCategory == null){
            return ResponseEntity.ok().body("Branch not found");
        }
        else{
            return ResponseEntity.ok().body(updatedCategory);
        }
    }

}
