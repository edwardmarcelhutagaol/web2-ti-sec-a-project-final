package com.example.inventory.controller;

import com.example.inventory.model.User;
import com.example.inventory.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String view(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        return "profile/view";
    }

    @GetMapping("/edit")
    public String editForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        model.addAttribute("user", user);
        return "profile/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        userService.updateProfile(user);
        return "redirect:/profile?success";
    }

    @GetMapping("/password")
    public String passwordForm() {
        return "profile/password";
    }

    @PostMapping("/password")
    public String updatePassword(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam String newPassword) {
        User user = userService.findByUsername(userDetails.getUsername()).orElseThrow();
        userService.updatePassword(user.getId(), newPassword);
        return "redirect:/profile?passwordSuccess";
    }
}
