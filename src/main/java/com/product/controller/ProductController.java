package com.product.controller;

import com.product.dto.ProductRequest;
import com.product.model.Product;
import com.product.service.ProductService;
import com.product.utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }



    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest) {

        Optional<Product> existingProduct = productService.getProductById(id);
        if(!existingProduct.isPresent()){
        }
        return productService.updateProduct(existingProduct.get(), productRequest);
    }

    @GetMapping("/products")
    public ResponseEntity<Object> getProduct(@RequestParam(name = "category", required = false) String category) {

        if (category != null) {
            Optional<Product> product = productService.getProductByCategory(category);
            if (product.isPresent()) {
                return ApiResponse.generateResponse(HttpStatus.OK.value(),  "Product in category: " + category + " fetched successfully", product, null);
            }
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product in category " + category + " not available in the database", null, "Product not Found");
        } else {
            List<Product> products = productService.getAllProducts();
            if (products.size() > 0) {
                return ApiResponse.generateResponse(HttpStatus.OK.value(), products.size() + " products available", products, null);
            }
            return ApiResponse.generateResponse(HttpStatus.OK.value(), products.size() + "Product not available in the database", null, "Product not Found");
        }
    }


    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id){
        Optional<Product> existingProduct = productService.getProductById(id);
        if(existingProduct.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product fetched successfully.", existingProduct, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Product with id "+ id + " not found in the database.", null, null);
    }


    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long id){
        Optional<Product> existingProduct = productService.getProductById(id);
        if(existingProduct.isPresent()){
            return ApiResponse.generateResponse(HttpStatus.OK.value(), "Product deleted successfully.", null, null);
        }
        return ApiResponse.generateResponse(HttpStatus.NOT_FOUND.value(), "Product with id "+ id + " not found in the database.", null, "Product not found.");
    }


    
}
