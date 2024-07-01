package com.example.iknow_assignment.service;


import com.example.iknow_assignment.model.Category;
import com.example.iknow_assignment.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    public CategoryServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetCategoryById(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        Category categoryReturned = categoryService.getCategoryById(1L);
        assertNotNull(categoryReturned);
        assertEquals("Electronics",categoryReturned.getName());
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setName("Electronics");
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category saved = categoryService.saveCategory(category);
        assertNotNull(saved);
        assertEquals("Electronics",saved.getName());
    }

    @Test
    public void testUpdateCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        Category updatedCategory = new Category();
        updatedCategory.setName("Updated Electronics");
        Category updated = categoryService.updateCategory(1L, updatedCategory);
        assertNotNull(updated);
        assertEquals("Updated Electronics",updated.getName());
    }

    @Test
    public void testDeleteCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(1L);
        categoryService.deleteCategory(1L);
        verify(categoryRepository,times(1)).deleteById(1L);
    }
}