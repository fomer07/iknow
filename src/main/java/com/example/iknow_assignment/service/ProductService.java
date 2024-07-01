package com.example.iknow_assignment.service;

import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.model.Product;
import com.example.iknow_assignment.repository.CategoryRepository;
import com.example.iknow_assignment.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAllProducts() {
        logger.debug("Fetching all products");
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        logger.debug("Fetching product by id: " + id);
        return productRepository.findById(id).orElse(null);
    }

    public Product saveProduct(Product product, String categoryName) {
        logger.debug("Saving product: " + product.getName());
        Category category = categoryRepository.findByName(categoryName)
                .orElseThrow(()-> {
                    logger.error("Category '{}' not found", categoryName);
                   return new RuntimeException("Category not found");
                });
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails,String categoryName) {
        logger.debug("Updating product with id : " + id);
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setName(productDetails.getName());
            Category category = categoryRepository.findByName(categoryName)
                            .orElseThrow(()-> {
                                    logger.error("Category '{}' not found", categoryName);
                                return new RuntimeException("Category not found");
                            });
            product.setCategory(category);
            return productRepository.save(product);
        }else {
            logger.error("Product with id " + id + " not found");
            return null;
        }
    }

    public void deleteProduct(Long id) {
        logger.debug("Deleting product with id : " + id);
        productRepository.deleteById(id);
    }
}
