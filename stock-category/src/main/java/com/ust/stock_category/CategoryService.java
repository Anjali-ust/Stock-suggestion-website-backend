package com.ust.stock_category;

import com.ust.stock_category.model.Category;
import com.ust.stock_category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(String id){
        return categoryRepository.findById(id);
    }

    public String deleteCategoryById(String id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            categoryRepository.deleteById(id);
            return "Category deleted";
        }
        else{
            return "Cannot find category";
        }
    }

    public Category updateCategory(String id,Category category){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if(categoryOptional.isPresent()){
            Category existingCategory = categoryOptional.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }
}
