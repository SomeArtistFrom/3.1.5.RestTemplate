package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.MyUserDetailsService;
import ru.kata.spring.boot_security.demo.services.UserService;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class RESTUserController {
    private final MyUserDetailsService myUserDetailsService;


    @Autowired
    public RESTUserController(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @GetMapping
    public ResponseEntity getAuthorizedUser(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
        User x = myUserDetailsService.findByUsername(user.getUsername());
        return new ResponseEntity<>(x, HttpStatus.OK);
    }


    //    @GetMapping
//    public ResponseEntity<User> getAuthorizedUser(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
//        if(user!=null){
//        return new ResponseEntity<>(myUserDetailsService.findByUsername(user.getUsername()), HttpStatus.OK);}
//        else {
//            return null;}
//    }

//    @GetMapping
//    public ResponseEntity<User> getAuthorizedUser(@AuthenticationPrincipal org.springframework.security.core.userdetails.User user) {
//        if(user!=null){
//            return new ResponseEntity<>(myUserDetailsService.findByUsername(user.getUsername()), HttpStatus.OK);}
//        else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
//    }

}