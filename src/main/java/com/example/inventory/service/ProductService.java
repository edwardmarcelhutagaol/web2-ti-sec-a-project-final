package com.example.inventory.service;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.User;
import com.example.inventory.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> searchProducts(User user, String name, Category category, Pageable pageable) {
        if (category != null) {
            return productRepository.findByUserAndCategoryAndNameContainingIgnoreCase(user, category, name, pageable);
        }
        return productRepository.findByUserAndNameContainingIgnoreCase(user, name, pageable);
    }

    public Product save(Product product, User user) {
        product.setUser(user);
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findAllByUser(Long userId) {
        return productRepository.findByUserId(userId);
    }

    public List<Product> findLowStock(Long userId) {
        return productRepository.findByUserIdAndStockLessThan(userId, 5);
    }
}
