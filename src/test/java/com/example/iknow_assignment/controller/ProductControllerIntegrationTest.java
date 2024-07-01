package com.example.iknow_assignment.controller;


import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.model.Product;
import com.example.iknow_assignment.repository.CategoryRepository;
import com.example.iknow_assignment.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
        categoryRepository.deleteAll();
        category = new Category();
        category.setName("Electronics");
        category = categoryRepository.save(category);
    }

    @Test
    public void testCreateProduct() throws Exception {
        String productJson = "{\"name\": \"Smartphone\",\"categoryName\":\"Electronics\"}";
        mockMvc.perform(post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Smartphone"));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setName("Smartphone");
        product.setCategory(category);
        productRepository.save(product);
        mockMvc.perform(get("/products/"+product.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Smartphone"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setName("Smartphone");
        product.setCategory(category);
        productRepository.save(product);
        String updatedProductJson = "{\"name\": \"Smartphone\",\"categoryName\":\"Electronics\"}";
        mockMvc.perform(put("/products/"+product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedProductJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Smartphone"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product product = new Product();
        product.setName("Smartphone");
        product.setCategory(category);
        product = productRepository.save(product);
        mockMvc.perform(delete("/products/"+product.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/products/"+product.getId()))
                .andExpect(status().isNotFound());
    }


}
