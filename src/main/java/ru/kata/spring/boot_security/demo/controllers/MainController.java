package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.services.MyUserDetailsService;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {
    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public MainController(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping
    public String showMainPage(Model model, Principal principal) {
        model.addAttribute("authorizedUser", myUserDetailsService.findByUsername(principal.getName()));
        return "main";
    }
}