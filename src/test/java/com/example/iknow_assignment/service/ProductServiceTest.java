package com.example.iknow_assignment.service;

import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.model.Product;
import com.example.iknow_assignment.repository.CategoryRepository;
import com.example.iknow_assignment.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    public ProductServiceTest () {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductById(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Product found = productService.getProductById(1L);
        assertNotNull(found);
        assertEquals("Smartphone", found.getName());
    }

    @Test
    public void testSaveProduct(){
        Product product = new Product();
        product.setName("Smartphone");
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product saved = productService.saveProduct(product,"Electronics");
        assertNotNull(saved);
        assertEquals("Smartphone", saved.getName());
    }

    @Test
    public void testUpdateProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Smartphone");

        Product updated = productService.updateProduct(1L,updatedProduct,"Electronics");
        assertNotNull(updated);
        assertEquals("Updated Smartphone", updated.getName());
    }

    @Test
    public void testDeleteProduct(){
        Product product = new Product();
        product.setId(1L);
        product.setName("Smartphone");
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById(1L);
        productService.deleteProduct(1L);
        verify(productRepository,times(1)).deleteById(1L);
    }

}