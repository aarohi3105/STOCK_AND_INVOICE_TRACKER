package com.project.stock.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.stock.entity.Product;
import com.project.stock.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;

    // 🔍 Search product by name (case-insensitive)
    public List<Product> searchByName(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    // 📊 Inventory Report — all products with stock summary
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ❌ Out of Stock — quantity == 0
    public List<Product> getOutOfStockProducts() {
        return productRepository.findByQuantity(0);
    }

    // ⚠ Low Stock — quantity <= lowStockThreshold
    public List<Product> getLowStockProducts() {
        return productRepository.findLowStock();
    }
    @org.springframework.transaction.annotation.Transactional
    public Product saveProduct(Product product) {

        // 🔥 basic validation
        if(product.getPrice() <= 0){
            throw new RuntimeException("Price must be greater than 0");
        }

        if(product.getQuantity() < 0){
            throw new RuntimeException("Quantity cannot be negative");
        }

        return productRepository.save(product);
    }

    // 🔍 Get product by ID
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // 🗑️ Delete product by ID
    @org.springframework.transaction.annotation.Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}