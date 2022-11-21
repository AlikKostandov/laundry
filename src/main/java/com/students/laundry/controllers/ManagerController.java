package com.students.laundry.controllers;

import com.students.laundry.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ManagerController {

    UserService userService;

    public ManagerController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/manager")
    public String showManagerPage(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "manager-page";
    }

    @PostMapping("user/add")
    public String addNewUser(@RequestParam String passNumber,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String room) {
        userService.saveOrUpdate(passNumber, name, surname, room);
        return "redirect:/manager";
    }

    @GetMapping("/manager/remove/{passNumber}")
    public String removeUserByPassNumber(@PathVariable String passNumber) {
        userService.deleteByPassNumber(passNumber);
        return "redirect:/manager";
    }

    @GetMapping("/manager/edit/{passNumber}")
    public String showEditUserForm(@PathVariable String passNumber, Model model) {
        model.addAttribute("user", userService.findByPassNumber(passNumber));
        return "user-edit-page";
    }

    @PostMapping("/manager/edit")
    public String editUser(@RequestParam String passNumber,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String room,
                           @RequestParam String roleCode) {
        userService.changeUserRole(passNumber, name, surname, room, null);
        return "redirect:/manager";
    }
}
