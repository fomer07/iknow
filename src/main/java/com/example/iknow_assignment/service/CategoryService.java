package com.example.iknow_assignment.service;

import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.repository.CategoryRepository;
import jakarta.persistence.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        logger.debug("Fetching all categories");
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id){
        logger.debug("Fetching category with ID: {}" + id);
        return categoryRepository.findById(id).orElse(null);
    }

    public Category saveCategory(Category category){
        logger.debug("Saving category: {}", category.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails){
        logger.debug("Updating category with ID: {}" + id);
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null){
            category.setName(categoryDetails.getName());
            category.setProducts(categoryDetails.getProducts());
            return categoryRepository.save(category);
        }
        return null;
    }

    public void deleteCategory(Long id){
        logger.debug("Deleting category with ID: {}" + id);
        categoryRepository.deleteById(id);
    }

    public String deleteAllCategories(){
        logger.debug("Deleting all products");
        categoryRepository.deleteAll();
        return "All categories deleted successfully";
    }

}
