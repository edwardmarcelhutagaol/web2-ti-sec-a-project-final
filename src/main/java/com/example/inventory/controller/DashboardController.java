package com.example.inventory.controller;

import com.example.inventory.model.Category;
import com.example.inventory.model.Product;
import com.example.inventory.model.User;
import com.example.inventory.service.CategoryService;
import com.example.inventory.service.ProductService;
import com.example.inventory.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class DashboardController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final UserService userService;

    public DashboardController(ProductService productService, CategoryService categoryService,
            UserService userService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails == null) {
            return "redirect:/login";
        }
        
        try {
            User user = userService.findByUsername(userDetails.getUsername()).orElse(null);
            
            if (user == null) {
                return "redirect:/login?logout";
            }
            
            List<Product> products = productService.findAllByUser(user.getId());

            double totalInventoryValue = products.stream()
                    .mapToDouble(p -> p.getPrice() * p.getStock())
                    .sum();

            long activeProducts = products.stream().filter(Product::isActive).count();
            long inactiveProducts = products.size() - activeProducts;

            Map<String, Long> productsByCategory = products.stream()
                    .filter(p -> p.getCategory() != null)
                    .collect(Collectors.groupingBy(p -> p.getCategory().getName(), Collectors.counting()));

            List<Product> lowStockProducts = products.stream()
                    .filter(p -> p.getStock() < 5)
                    .collect(Collectors.toList());

            model.addAttribute("totalProducts", products.size());
            model.addAttribute("totalValue", totalInventoryValue);
            model.addAttribute("activeProducts", activeProducts);
            model.addAttribute("inactiveProducts", inactiveProducts);
            model.addAttribute("productsByCategory", productsByCategory);
            model.addAttribute("lowStockProducts", lowStockProducts);
            model.addAttribute("hasProducts", !products.isEmpty());

            return "dashboard";
        } catch (Exception e) {
            System.err.println("Error rendering dashboard: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
