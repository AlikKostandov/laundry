package com.students.laundry.controllers;


import com.students.laundry.entities.User;
import com.students.laundry.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class RegistrationController {

    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registry")
    public String showRegistryForm(Model model) {
        model.addAttribute("user", new User());
        return "registration-page";
    }


    @PostMapping("/registry")
    public String updateUser(@RequestParam String passNumber, @RequestParam String password){
        userService.savePasswordForUser(passNumber, password);
        return "login-page";
    }
}
