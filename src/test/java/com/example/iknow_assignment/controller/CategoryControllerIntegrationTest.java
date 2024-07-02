package com.example.iknow_assignment.controller;

import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.repository.CategoryRepository;
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
public class CategoryControllerIntegrationTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryRepository.deleteAll();
    }

    @Test
    public void testCreateCategory() throws Exception {
        String categoryJson = "{\"name\":\"Electronics\"}";
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(categoryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"));
    }

    @Test
    public void testGetCategoryById() throws Exception {
        Category category = new Category();
        category.setName("Electronics");
        category = categoryRepository.save(category);
        mockMvc.perform(get("/categories/" + category.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Electronics"));
    }

    @Test
    public void testUpdateCategory() throws Exception {
        Category category = new Category();
        category.setName("Electronics");
        category = categoryRepository.save(category);
        String updatedCategoryJson = "{\"name\":\"Updated Electronics\"}";
        mockMvc.perform(put("/categories/" + category.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(updatedCategoryJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Electronics"));
    }

    @Test
    public void testDeleteCategory() throws Exception {
        Category category = new Category();
        category.setName("Electronics");
        category = categoryRepository.save(category);
        mockMvc.perform(delete("/categories/" + category.getId()))
                .andExpect(status().isOk());
        mockMvc.perform(get("/categories/" + category.getId()))
                .andExpect(status().isOk());
    }
}
