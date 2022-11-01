package com.students.laundry.controllers;

import com.students.laundry.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class HomeController {

    private final SessionService sessionService;

    @GetMapping("/home")
    public String showHome(Model model) {
        model.addAttribute("sessions", sessionService.getAllWindows());
        return "home-page";
    }
}
