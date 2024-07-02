package com.example.iknow_assignment.controller;

import com.example.iknow_assignment.model.Product;
import com.example.iknow_assignment.model.ProductRequest;
import com.example.iknow_assignment.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Tag(name = "Product", description = "API for Product management")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    @Operation(summary = "Get all products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a product by ID")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new product")
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        return productService.saveProduct(productRequest.toProduct(),productRequest.getCategoryName());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a product")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest.toProduct(),productRequest.getCategoryName());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

}
