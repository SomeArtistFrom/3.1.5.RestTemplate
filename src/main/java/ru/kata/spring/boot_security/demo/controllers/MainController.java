package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;


import java.security.Principal;

@Controller
@RequestMapping("/main")
public class MainController {
    private final UserService userService;
@Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping
//    public String showMainPage(Model model, Principal principal) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        org.springframework.security.core.userdetails.User userSecurity =
//                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
//        User user = userService.getUserByUsername(userSecurity.getUsername());
//        System.out.println("1" + user);
//        model.addAttribute("authorizedUser", user);
//        System.out.println(principal);
//        return "main";
//    }
@GetMapping
public String showMainPage(Model model, Principal principal) {
    model.addAttribute("authorizedUser", userService.getUserByUsername(principal.getName()));
    return "main";
}


    }