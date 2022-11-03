package com.students.laundry.controllers;

import com.students.laundry.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@AllArgsConstructor
public class HomeController {

    private final SessionService sessionService;

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("sessions", sessionService.getAllWindows());
        return "home-page";
    }

    @PostMapping("home/{id}")
    public String showSession(@PathVariable Long id, Model model) {
        model.addAttribute("sessions", sessionService.findById(id));
        return "redirect:/home";
    }

}
