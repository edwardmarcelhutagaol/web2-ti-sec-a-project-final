package com.example.inventory.controller;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.User;
import com.example.inventory.service.CategoryService;
import com.example.inventory.service.ProductService;
import com.example.inventory.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public ProductController(ProductService productService, CategoryService categoryService, UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping
    public String list(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "") String search,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(defaultValue = "0") int page,
            Model model) {
        
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        Category category = (categoryId != null) ? categoryService.findById(categoryId) : null;
        
        Page<Product> productPage = productService.searchProducts(user, search, category, PageRequest.of(page, 10));
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("totalElements", productPage.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("categoryId", categoryId);
        model.addAttribute("categories", categoryService.findAllByUser(user));
        
        return "product/list";
    }

    @GetMapping("/add")
    public String addForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAllByUser(user));
        return "product/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categories", categoryService.findAllByUser(user));
        return "product/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Product product, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        productService.save(product, user);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}
