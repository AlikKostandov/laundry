package com.students.laundry.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@AllArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String showHome() {
        return "home-page";
    }
}
