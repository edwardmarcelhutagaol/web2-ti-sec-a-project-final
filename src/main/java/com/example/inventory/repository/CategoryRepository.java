package com.example.inventory.repository;

import com.example.inventory.model.Category;
import com.example.inventory.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByUser(User user);
    Page<Category> findByUser(User user, Pageable pageable);
    List<Category> findByUserId(Long userId);
}
