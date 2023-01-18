package com.karaculya.shop.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {

    @GetMapping("/")
    public String openIndexPage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "index";
    }

    @GetMapping("/about")
    public String openAboutPage(Model model) {
        model.addAttribute("title", "О нас");
        return "about";
    }
}