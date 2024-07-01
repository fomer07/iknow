package com.example.iknow_assignment.controller;


import com.example.iknow_assignment.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;

public class ProductRequest {

    @Schema(description = "Name of the product", example = "Smartphone")
    private String name;

    @Schema(description = "Name of the category", example = "Electronics")
    private String categoryName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Product toProduct(){
        Product product = new Product();
        product.setName(this.name);
        return product;
    }
}
