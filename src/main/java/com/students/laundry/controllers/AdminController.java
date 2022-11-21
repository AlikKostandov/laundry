package com.students.laundry.controllers;

import com.students.laundry.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/admin/edit/{passNumber}")
    public String showEditUserForm(@PathVariable String passNumber, Model model) {
        model.addAttribute("user", userService.findByPassNumber(passNumber));
        return "user-edit-page";
    }

    @PostMapping("/admin/edit")
    public String editUser(@RequestParam String passNumber,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String room,
                           @RequestParam String roleCode) {
        userService.changeUserRole(passNumber, name, surname, room, roleCode);
        return "redirect:/admin";
    }

}
