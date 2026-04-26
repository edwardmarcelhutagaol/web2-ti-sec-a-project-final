package com.example.inventory.service;

import com.example.inventory.model.Category;
import com.example.inventory.model.User;
import com.example.inventory.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAllByUser(User user) {
        return categoryRepository.findByUser(user);
    }

    public Page<Category> findAllByUser(User user, Pageable pageable) {
        return categoryRepository.findByUser(user, pageable);
    }

    public Category save(Category category, User user) {
        category.setUser(user);
        return categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
