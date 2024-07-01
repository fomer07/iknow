package com.example.iknow_assignment.service;

import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.repository.CategoryRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveCategory(Category category){
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails){
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null){
            category.setName(categoryDetails.getName());
            category.setProducts(categoryDetails.getProducts());
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }

}
