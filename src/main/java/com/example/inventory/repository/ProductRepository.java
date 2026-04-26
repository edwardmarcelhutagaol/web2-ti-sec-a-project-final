package com.example.inventory.repository;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findByUserAndNameContainingIgnoreCase(User user, String name, Pageable pageable);
    Page<Product> findByUserAndCategoryAndNameContainingIgnoreCase(User user, Category category, String name, Pageable pageable);
    List<Product> findByUserId(Long userId);
    List<Product> findByUserIdAndStockLessThan(Long userId, int stockThreshold);
}
