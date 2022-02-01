package com.product.service;

import com.product.dto.ProductRequest;
import com.product.model.Product;
import com.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(ProductRequest productRequest) {
        Product newProduct = new Product();

        newProduct.setProductName(productRequest.getProductName());
        newProduct.setCategory(productRequest.getCategory());
        newProduct.setUnitPrice(productRequest.getUnitPrice());
        newProduct.setQuantityAvailable(productRequest.getQuantityAvailable());
        return productRepository.save(newProduct);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product updateProduct(Product product, ProductRequest productRequest) {

        product.setProductName(productRequest.getProductName());
        product.setCategory(productRequest.getCategory());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setQuantityAvailable(productRequest.getQuantityAvailable());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public Optional<Product> getProductByCategory(String category) {
        return productRepository.findByCategory(category);

    }
}
