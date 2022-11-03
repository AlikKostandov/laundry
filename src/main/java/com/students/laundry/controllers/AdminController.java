package com.students.laundry.controllers;

import com.students.laundry.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller

public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String showAdminPage( Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-page";
    }


    @GetMapping("/admin/remove/{passNumber}")
    public String removeUserByPassNumber(@PathVariable String passNumber) {
        userService.deleteByPassNumber(passNumber);
        return "redirect:/admin";
    }
}
