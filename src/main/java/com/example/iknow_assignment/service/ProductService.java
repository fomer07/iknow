package com.example.iknow_assignment.service;

import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.model.Product;
import com.example.iknow_assignment.repository.CategoryRepository;
import com.example.iknow_assignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product, String categoryName) {
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(()-> new RuntimeException("Category not found"));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails,String categoryName) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.getName());
            Category category = categoryRepository.findByName(categoryName)
                            .orElseThrow(()-> new RuntimeException("Category not found"));
            product.setCategory(category);
            return productRepository.save(product);
        }
        return null;
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
